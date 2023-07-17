package com.test.contactapp.util

sealed class ApiState<out T>{
    data class Success<out R> (val data:R) : ApiState<R>()
    data class Failure(val message:String) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
}
