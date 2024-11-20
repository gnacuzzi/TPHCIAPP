package com.example.peraapp.data.network

import com.example.peraapp.data.network.api.UserApiService
import com.example.peraapp.data.network.model.NetworkCredentials
import com.example.peraapp.data.network.model.NetworkUser
import com.example.peraapp.SessionManager

class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val userApiService: UserApiService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            userApiService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { userApiService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser(): NetworkUser {
        return handleApiResponse { userApiService.getCurrentUser() }
    }
}