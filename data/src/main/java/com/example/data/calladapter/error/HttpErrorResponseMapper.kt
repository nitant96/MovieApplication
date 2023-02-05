package com.example.data.calladapter.error

import com.google.gson.Gson
import com.example.data.DataError
import com.example.domain.DomainErrorBlock
import javax.inject.Inject

class HttpErrorResponseMapper @Inject constructor(private val gson: Gson) {

    fun mapTo(errorBody: String): DomainErrorBlock {
        return try {
            val dataErrorBlock = gson.fromJson(errorBody, DataError::class.java)
            val lastItem = dataErrorBlock.requestResult.errors?.last()
            DomainErrorBlock(lastItem?.errorCode?: "", lastItem?.errorMessage?: "")
        } catch (e: Exception) {
            e.printStackTrace()
            DomainErrorBlock("", "")
        }
    }
}

