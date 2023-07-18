package com.test.contactapp.ui

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.contactapp.data.model.Data
import com.test.contactapp.data.model.LoginRequest
import com.test.contactapp.data.repository.ContactListDataSource
import com.test.contactapp.data.repository.ContactRepository
import com.test.contactapp.util.ApiState
import com.test.contactapp.util.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: ContactRepository
): ViewModel() {

    lateinit var naveHostController: NavHostController

    var isCustomDialogShown by mutableStateOf(false)
    var isLoadingDialogShown by mutableStateOf(false)
    var message by mutableStateOf("")
        private set


    private fun navigateToContactListScreen() {
        naveHostController.navigate(Destination.ContactList.rout)
    }
    fun navigateToAddContactScreen() {
        naveHostController.navigate(Destination.AddContact.rout)
    }


    fun showCustomDialog(message:String){
        this.message = message
        isCustomDialogShown = true
    }
    fun dismissCustomDialog(){
        isCustomDialogShown = false
    }

    fun showLoadingDialog(){
        isLoadingDialogShown = true
    }
    fun dismissLoadingDialog(){
        isLoadingDialogShown = false
    }

    fun userLogin(userName: String, password: String) {
        if (userName.isNotEmpty() && password.isNotEmpty()){
            val loginRequest = LoginRequest(
                userName,
                password
            )
            viewModelScope.launch {
                repository.loginUser(loginRequest).collect{
                    when(it){
                        is ApiState.Loading ->{
                            showLoadingDialog()
                            Log.d("MyTag", "Loading")
                        }
                        is ApiState.Success ->{
                            Log.d("MyTag", "Success")
                            dismissLoadingDialog()
                            val token = it.data!!.token
                            navigateToContactListScreen()

                        }
                        is ApiState.Failure ->{
                            dismissLoadingDialog()
                            showCustomDialog("No Record Found")
                            Log.d("MyTag", it.message)
                        }
                    }
                }
            }
        }
        else{
            showCustomDialog("Invalid Credential")
        }
    }


    //Paging
    fun getContactList() : Flow<PagingData<Data>> = Pager(
        config = PagingConfig(pageSize = 6, enablePlaceholders = false)
    ){
        ContactListDataSource(repository)
    }.flow.cachedIn(viewModelScope)
}