package com.test.contactapp.data.model

data class ContactListPagedResponse(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)