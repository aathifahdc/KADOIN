package com.example.kadoin.ui.home

data class GiftItem(
    val image: String,
    val name: String,
    val price: String,
    val rating: Float = 0f,
    val reviews: String = "",
    val discount: String = "",
    val originalPrice: String = "",
    val sold: String = ""

)
