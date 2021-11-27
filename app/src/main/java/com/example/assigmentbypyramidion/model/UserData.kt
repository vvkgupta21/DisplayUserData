package com.example.assigmentbypyramidion.model

data class UserData(
    val data: ArrayList<UserDetail>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)

data class UserDetail(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
)

data class Support(
    val text: String,
    val url: String
)