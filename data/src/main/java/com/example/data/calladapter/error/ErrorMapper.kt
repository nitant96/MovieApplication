package com.example.data.calladapter.error

import com.example.data.ApiError
import javax.inject.Inject
import com.example.domain.Error as DomainError

class ErrorMapper @Inject constructor() {

    @Inject
    lateinit var httpErrorResponseMapper: HttpErrorResponseMapper

    fun mapToDomainError(error: ApiError): DomainError = with(error) {
        return when {
            isServerError -> DomainError.ApiError.ServiceUnavailable
            isHttpError -> getClientError(this)
            isNetworkError -> DomainError.ApiError.NetworkError(networkErrorMessage)
            isUnknownError -> DomainError.ApiError.Unknown(unknownErrorMessage)

            else -> throw IllegalStateException("Could not map the api error $this to a domain error.")
        }
    }

    private fun getClientError(error: ApiError): com.example.domain.Error {
        val errorBlock = httpErrorResponseMapper.mapTo(error.httpErrorBody)
        return DomainError.ApiError.ClientError(
            error.httpErrorCode,
            errorBlock.errorMsg,
            errorBlock
        )
    }
}

