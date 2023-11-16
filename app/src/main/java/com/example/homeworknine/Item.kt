package com.example.homeworknine

data class Item(
    val id: Int,
    val imageView: Int,
    val title: String,
    val price: Int,
    val categoryType: CategoryType
)

enum class CategoryType{
    ALL,
    PARTY,
    CAMPING,
    BUSINESS,
    SPORTS,
    WINTER
}
