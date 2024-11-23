package com.example.peraapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.peraapp.data.DataSourceException
import com.example.peraapp.data.model.BalancePayment
import com.example.peraapp.data.model.Card
import com.example.peraapp.data.model.CardPayment
import com.example.peraapp.data.model.Error
import com.example.peraapp.data.model.RegisterUser
import com.example.peraapp.data.model.User
import com.example.peraapp.data.model.VerifyCode
import com.example.peraapp.data.repository.PaymentRepository
import com.example.peraapp.data.repository.UserRepository
import com.example.peraapp.data.repository.WalletRepository
import com.example.peraapp.navigation.AppDestinations
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val walletRepository: WalletRepository,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    private var walletDetailStreamJob: Job? = null
    private var creditCardsStreamJob: Job? = null
    private val _uiState = MutableStateFlow(HomeUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    private val _shouldNavigate = MutableStateFlow<String?>(null)
    val shouldNavigate: StateFlow<String?> = _shouldNavigate

    init {
        if (uiState.value.isAuthenticated) {
            observeWalletDetailStream()
            observeCreditCardsStream()
        }
    }

    fun signin(user: RegisterUser) = runOnViewModelScope(
        {
            userRepository.signin(user)
        },
        {
            state, _ -> state.copy()
        }
    )

    fun login(username: String, password: String) = runOnViewModelScope(
        {
            userRepository.login(username, password)
            observeWalletDetailStream()
            observeCreditCardsStream()
        },
        { state, _ -> _shouldNavigate.value = AppDestinations.INICIO.route
            state.copy(isAuthenticated = true) }
    )

    fun clearNavigationFlag() {
        _shouldNavigate.value = null
    }

    fun logout() = runOnViewModelScope(
        {
            walletDetailStreamJob?.cancel()
            creditCardsStreamJob?.cancel()
            userRepository.logout()
        },
        { state, _ ->
            state.copy(
                isAuthenticated = false,
                walletDetail = null,
                cards = null
            )
        }
    )

    fun verify(verifyCode: VerifyCode) = runOnViewModelScope(
        {
            userRepository.verify(verifyCode)
        },
        {
                state, _ -> state.copy()
        }
    )

    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.value.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )

    fun addCard(card: Card) = runOnViewModelScope(
        {
            walletRepository.addCard(card)
        },
        { state, response ->
            state.copy(
                currentCard = response,
                cards = null
            )
        }
    )

    var currentPage = 1

    private var _hasMorePayments = MutableStateFlow(true) // Inicialmente asume que hay más pagos
    val hasMorePayments: StateFlow<Boolean> = _hasMorePayments.asStateFlow()

    fun loadPayments(page: Int) = runOnViewModelScope(
        {
            val result = paymentRepository.getPayments(page)
            _hasMorePayments.value = result.isNotEmpty() // Determina si hay más datos
            result
        },
        { state, response ->
            state.copy(payments = response)
        }
    )

    fun nextPage() {
        if (_hasMorePayments.value) {
            currentPage++
            loadPayments(currentPage)
        }
    }



    fun previousPage() {
        if (currentPage > 1) {
            currentPage--
            loadPayments(currentPage)
        }
    }

    fun getPayments() = runOnViewModelScope(
        { paymentRepository.getPayments(currentPage) },
        { state, response -> state.copy(payments = response) }
    )

    fun deleteCard(cardId: Int) = runOnViewModelScope(
        { walletRepository.deleteCard(cardId) },
        { state, _ ->
            state.copy(
                currentCard = null,
                cards = null
            )
        }
    )

    fun makeBalancePayment(balancePayment: BalancePayment) = runOnViewModelScope(
        { paymentRepository.makeBalancePayment(balancePayment) },
        { state, _ ->
            state.copy(
                payments = null //no se si esto esta bien
            )
        }
    )

    fun makeCardPayment(cardPayment: CardPayment) = runOnViewModelScope(
        { paymentRepository.makeCardPayment(cardPayment) },
        { state, _ ->
            state.copy(
                payments = null //no se si esto esta bien
            )
        }
    )


    private fun observeWalletDetailStream() {
        walletDetailStreamJob = collectOnViewModelScope(
            walletRepository.walletDetailStream
        ) { state, response -> state.copy(walletDetail = response) }
    }

    private fun observeCreditCardsStream() {
        creditCardsStreamJob = collectOnViewModelScope(
            walletRepository.getCardsStream
        ) { state, response -> state.copy(cards = response) }
    }

    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (HomeUiState, T) -> HomeUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiState.update { currentState -> currentState.copy(error = handleError(e)) } }
            .collect { response -> _uiState.update { currentState -> updateState(currentState, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (HomeUiState, R) -> HomeUiState
    ): Job = viewModelScope.launch {
        _uiState.update { currentState -> currentState.copy(isFetching = true, error = null) }
        runCatching {
            block()
        }.onSuccess { response ->
            _uiState.update { currentState -> updateState(currentState, response).copy(isFetching = false) }
        }.onFailure { e ->
            _uiState.update { currentState -> currentState.copy(isFetching = false, error = handleError(e)) }
            Log.e(TAG, "Coroutine execution failed", e)
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "")
        } else {
            Error(null, e.message ?: "")
        }
    }

    companion object {
        const val TAG = "UI Layer"

        fun provideFactory(
            application: PeraApplication
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    application.sessionManager,
                    application.userRepository,
                    application.walletRepository,
                    application.paymentRepository) as T
            }
        }
    }
}