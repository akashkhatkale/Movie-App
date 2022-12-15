package com.movee.movee.commons.extensions

import com.movee.movee.commons.constants.ApiConstants


fun getImageUrl(backdropPath: String, size: ApiConstants.ImageWidth = ApiConstants.ImageWidth.WIDTH_500): String {
    return ApiConstants.IMAGE_URL + size.width + backdropPath
}


fun <T> List<T>.getTrimmedList(maximum: Int): List<T> {
    return if (size < maximum) {
        this
    } else {
        subList(0, 5)
    }
}