package com.movee.movee.domain.entities

open class FeedItem(
    val uiItemType : String,
    val title : String,
    val subtitle : String
)

class HorizontalMoviesItem(
    uiItemType : String,
    title : String,
    subtitle : String,
    val response: MoviesResponse
) : FeedItem(uiItemType, title, subtitle)
