package com.test.contactapp.data.service

import com.test.contactapp.data.model.*
import retrofit2.Response
import javax.inject.Inject

class ContactApiService @Inject constructor(private val api: ContactApi){

    suspend fun login(
        loginRequest: LoginRequest
    ): Response<LoginResponse> = api.login(loginRequest)
    suspend fun addContact(
        contactRequest: ContactRequest
    ): Response<AddContactResponse> = api.addContact(contactRequest)


    suspend fun getContactPagedList(
        pageNumber: Int,
    ): SimpleResponse<ContactListPagedResponse> {
        return safeApiCall { api.getContactPagedList(pageNumber, 6) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }

}