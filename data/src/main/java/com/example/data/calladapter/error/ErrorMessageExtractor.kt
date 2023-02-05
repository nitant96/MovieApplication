package com.example.data.calladapter.error

interface ErrorMessageExtractor {
    fun extract(responseBody:String): String
}