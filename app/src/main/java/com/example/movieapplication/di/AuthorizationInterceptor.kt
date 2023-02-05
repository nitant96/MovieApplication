package com.example.movieapplication.di

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthorizationInterceptor(private val authorizer: Authorizer) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = authorizer.getAuthToken()
        val builder = chain.request().newBuilder()
        if (!authToken.isNullOrBlank()) {
            builder.addHeader(Headers.AUTHORIZATION, "Bearer $authToken")
        }
        return chain.proceed(builder.build())
    }

}

