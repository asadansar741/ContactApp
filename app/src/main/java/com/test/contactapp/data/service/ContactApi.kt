package com.test.contactapp.data.service


import com.test.contactapp.data.model.LoginRequest
import com.test.contactapp.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.*


interface ContactApi {

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>





}