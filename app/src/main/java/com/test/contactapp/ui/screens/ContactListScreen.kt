package com.test.contactapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.test.contactapp.data.model.Data
import com.test.contactapp.ui.MainViewModel
import com.test.contactapp.ui.theme.AppColor
import com.test.contactapp.ui.theme.ContactAppTheme
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter

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

    val contactList: LazyPagingItems<Data> = viewModel.getContactList().collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn {
            items(items = contactList) { contact ->
                EachRow(contact)
            }
        }
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


@Composable
fun EachRow(item: Data?) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        backgroundColor = Color.Gray
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfileCard(item)
        }

    }
}


@Composable
fun ProfileCard(item: Data? ) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable {  },
        backgroundColor = Color.White
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            item?.apply {
                ProfilePicture(avatar)
                ProfileContent(
                    firstName = first_name ,
                    lastName = last_name
                )
            }
        }
    }
}


@Composable
fun ProfilePicture(imageUrl:String) {
    Card(
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .padding(5.dp)
            .width(100.dp)
            .height(128.dp)
    )
    {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
    }
}

@Composable
fun ProfileContent(firstName:String, lastName:String ) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = firstName,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
        CompositionLocalProvider(LocalContentAlpha provides (ContentAlpha.medium)) {
            Text(
                text = lastName,
                style = MaterialTheme.typography.body2
            )
        }
    }

}
