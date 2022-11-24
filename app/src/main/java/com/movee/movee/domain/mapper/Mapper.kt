package com.movee.movee.domain.mapper

interface Mapper<in I, out O> {
    fun map(input: I) : O
}