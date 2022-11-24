package com.movee.movee.commons.exceptions

import com.movee.movee.commons.constants.ErrorConstants.INTERNET_CONNECTION_ERROR


class NoInternetConnectionException : Exception(INTERNET_CONNECTION_ERROR)