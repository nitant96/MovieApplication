package com.example.movieapplication.di


import android.util.Log
import com.google.gson.Gson
import com.example.movieapplication.BuildConfig
import com.example.movieapplication.BuildConfig.BASE_URL
import com.example.data.calladapter.ApiResultCallAdapterFactory
import com.example.data.calladapter.error.ErrorMessageExtractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authorizationInterceptor: AuthorizationInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder().apply {
            addInterceptor(authorizationInterceptor)
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(httpLoggingInterceptor)
            }
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    fun providesOkhttpLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Provides
    @Singleton
    fun providesErrorExtractor(): ErrorMessageExtractor = object : ErrorMessageExtractor {
        override fun extract(responseBody: String): String {
            Log.e("Error", "Got the error :$responseBody")
            return responseBody
        }
    }


    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        errorMessageExtractor: ErrorMessageExtractor
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ApiResultCallAdapterFactory(errorMessageExtractor))
        .build()

    @Provides
    fun provideAuthorizationInterceptor(
        authorizer: Authorizer
    ): AuthorizationInterceptor {
        return AuthorizationInterceptor(authorizer)
    }

}

