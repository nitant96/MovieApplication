package com.example.domain

interface UseCase<In, Out> {
    suspend fun execute(params: In): Out
}

