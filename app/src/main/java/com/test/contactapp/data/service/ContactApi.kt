package com.test.contactapp.data.service


import com.test.contactapp.data.model.ContactListPagedResponse
import com.test.contactapp.data.model.LoginRequest
import com.test.contactapp.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.*


interface ContactApi {

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>


    @GET("users")
    suspend fun getContactPagedList(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ): Response<ContactListPagedResponse>





}