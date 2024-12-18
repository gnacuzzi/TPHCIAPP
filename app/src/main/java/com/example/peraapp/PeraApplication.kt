package com.example.peraapp

import android.app.Application
import com.example.peraapp.data.network.PaymentRemoteDataSource
import com.example.peraapp.data.network.UserRemoteDataSource
import com.example.peraapp.data.network.WalletRemoteDataSource
import com.example.peraapp.data.network.api.RetrofitClient
import com.example.peraapp.data.repository.PaymentRepository
import com.example.peraapp.data.repository.UserRepository
import com.example.peraapp.data.repository.WalletRepository

class PeraApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getUserApiService(this))

    private val walletRemoteDataSource: WalletRemoteDataSource
        get() = WalletRemoteDataSource(RetrofitClient.getWalletApiService(this))

    private val paymentRemoteDataSource: PaymentRemoteDataSource
        get() = PaymentRemoteDataSource(RetrofitClient.getPaymentApiService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val walletRepository: WalletRepository
        get() = WalletRepository(walletRemoteDataSource)

    val paymentRepository: PaymentRepository
        get() = PaymentRepository(paymentRemoteDataSource)
}