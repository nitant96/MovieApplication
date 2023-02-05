package com.example.domain

import com.github.michaelbull.result.Result

typealias DomainResult<T> = Result<T, Error>

typealias DomainErrorBlock = ErrorBlock