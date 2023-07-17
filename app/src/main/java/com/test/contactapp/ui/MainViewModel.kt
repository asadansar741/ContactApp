package com.test.contactapp.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.test.contactapp.util.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(): ViewModel() {

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
}