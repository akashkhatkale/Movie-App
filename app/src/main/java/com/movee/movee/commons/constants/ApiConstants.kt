package com.movee.movee.commons.constants

import com.movee.movee.commons.constants.LanguageConstants.EN_US

object ApiConstants {
    enum class ImageWidth(val width : String) {
        WIDTH_500("/w500"), WIDTH_780("/w780")
    }
    const val BASE_VERSION = "3"
    const val BASE_URL = "https://api.themoviedb.org/"
    const val BASE_LANGUAGE = EN_US
    const val IMAGE_URL = "https://image.tmdb.org/t/p"
}