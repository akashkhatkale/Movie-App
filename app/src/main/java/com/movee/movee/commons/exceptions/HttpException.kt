package com.movee.movee.commons.exceptions

import com.movee.movee.commons.constants.ErrorConstants.HTTP_ERROR

class HttpException(
    val statusCode : Int
) : Exception(HTTP_ERROR)