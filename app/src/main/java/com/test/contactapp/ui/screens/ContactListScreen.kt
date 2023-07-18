package com.test.contactapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.test.contactapp.ui.MainViewModel
import com.test.contactapp.ui.theme.AppColor
import com.test.contactapp.ui.theme.ContactAppTheme

@Composable
fun ContactListScreen(mainViewModel: MainViewModel){
    Scaffold(topBar = { AppBar("Contact List") }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            ContactAppTheme {
                ContactListScreenUI(mainViewModel)
            }
        }
    }
}



@Composable
fun ContactListScreenUI(viewModel: MainViewModel){

    val contactList = viewModel.getContactList().collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

    }

    Box (modifier = Modifier
        .padding(10.dp,10.dp),
        contentAlignment = Alignment.BottomEnd
    ){
        FloatingActionButton(shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 50)),
            backgroundColor = AppColor,
            contentColor = Color.White,
            onClick = { viewModel.navigateToAddContactScreen() }
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
        }
    }
}
