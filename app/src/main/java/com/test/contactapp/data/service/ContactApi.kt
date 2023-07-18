package com.test.contactapp.data.service


import com.test.contactapp.data.model.*
import retrofit2.Response
import retrofit2.http.*


interface ContactApi {

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
    @POST("users")
    suspend fun addContact(
        @Body contactRequest: ContactRequest
    ): Response<AddContactResponse>
    @POST("register")
    suspend fun registerUser(
        @Body userRegister: UserRegister
    ): Response<UserRegisterResponse>


    @GET("users")
    suspend fun getContactPagedList(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ): Response<ContactListPagedResponse>





}