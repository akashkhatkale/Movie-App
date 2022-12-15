package com.movee.movee.domain.entities

open class FeedItem(
    val uiItemType : String,
    val title : String,
    val subtitle : String
)

class HorizontalMoviesFeedItem(
    uiItemType : String,
    title : String,
    subtitle : String,
    val response: MoviesResponse
) : FeedItem(uiItemType, title, subtitle)

class HorizontalCarouselMoviesFeedItem(
    uiItemType : String,
    title : String,
    subtitle : String,
    val response: MoviesResponse
) : FeedItem(uiItemType, title, subtitle)

class HorizontalFullWidthCarouselMoviesFeedItem(
    uiItemType : String,
    title : String,
    subtitle : String,
    val response: MoviesResponse
) : FeedItem(uiItemType, title, subtitle)
