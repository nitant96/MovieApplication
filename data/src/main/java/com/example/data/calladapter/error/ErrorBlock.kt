package com.example.data.calladapter.error

import com.google.gson.annotations.SerializedName

data class ErrorBlock(
    @SerializedName("RequestResult")
    val requestResult: RequestResult = RequestResult()
) {
    data class RequestResult(
        @SerializedName("Errors") val errors: List<Errors>? = null
    ) {
        data class Errors(
            @SerializedName("ErrorCode") val errorCode: String = "",
            @SerializedName("ErrorMessage") val errorMessage: String = ""
        )
    }
}

