package com.example.domain

interface DataMapper<RESPONSE, MODEL> {
    fun mapTo(data: RESPONSE): MODEL
}

