package com.example.asd.apinet

//data class NewsApi(val results: List<Results>)

data class Results(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)