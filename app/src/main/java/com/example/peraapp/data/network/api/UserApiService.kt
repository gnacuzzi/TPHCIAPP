package com.example.peraapp.data.network.api

import com.example.peraapp.data.network.model.NetworkCredentials
import com.example.peraapp.data.network.model.NetworkRegisterUser
import com.example.peraapp.data.network.model.NetworkToken
import com.example.peraapp.data.network.model.NetworkUser
import com.example.peraapp.data.network.model.NetworkVerifyCode
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {

    @POST("user")
    suspend fun signin(@Body user: NetworkRegisterUser): Response<Unit>

    @POST("user/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("user/logout")
    suspend fun logout(): Response<Unit>

    @GET("user")
    suspend fun getCurrentUser(): Response<NetworkUser>

    @POST("user/verify")
    suspend fun verify(@Body verifyCode: NetworkVerifyCode) : Response<NetworkUser>
}