package com.test.contactapp.util

const val  LOGIN_SCREEN  = "LOGIN_SCREEN"
const val CONTACT_LIST_SCREEN = "CONTACT_LIST_SCREEN"
const val ADD_CONTACT_SCREEN = "ADD_CONTACT_SCREEN"

sealed class Destination(val rout:String){
    object Login : Destination(LOGIN_SCREEN)
    object ContactList : Destination(CONTACT_LIST_SCREEN)
    object AddContact : Destination(ADD_CONTACT_SCREEN)
}

