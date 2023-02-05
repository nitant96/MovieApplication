package com.example.data

import com.github.michaelbull.result.Result
import com.example.data.calladapter.error.Error
import com.example.data.calladapter.error.ErrorBlock

typealias ApiResult<T> = Result<T, Error>

typealias ApiError = Error

typealias DataError = ErrorBlock

