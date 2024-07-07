package com.reza.network.di

import com.google.gson.Gson
import com.reza.network.BuildConfig
import com.reza.network.data.api.ApiService
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


/**
 * Hilt module for providing network-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val HEADER_KEY = "x-api-key"
    private const val TIMEOUT_SECONDS = 30L

    /**
     * Provides an [HttpLoggingInterceptor] instance for logging network requests and responses.
     *
     * The interceptor is configured to log the request and response bodies.
     */
    @Singleton
    @Provides
    fun provideOkHttpLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    /**
     * Provides an [OkHttpClient] instance configured for network requests.
     *
     * The client includes timeouts, redirect handling, and an interceptor to add the API key header.
     * In debug builds, the [HttpLoggingInterceptor] is also added for logging.
     *
     * @param loggingInterceptor The [HttpLoggingInterceptor] instance to use for logging.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor { chain ->
                val newRequest =
                    chain.request().newBuilder().addHeader(HEADER_KEY, BuildConfig.API_KEY).build()
                chain.proceed(newRequest)
            }
        return if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor).build()
        } else {
            builder.build()
        }
    }

    /**
     * Provides a [Retrofit] instance configured for making API requests.
     *
     * The Retrofit instance uses Gson for serialization and deserialization,
     * and is configured with the base URL from [BuildConfig].
     *
     * @param okHttpClient The [OkHttpClient] instance to use for network requests.
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

    /**
     * Provides an [ApiService] instance for making API requests.
     *
     * @param retrofit The [Retrofit] instance used to create the service.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    /**
     * Provides a [Gson] instance for JSON serialization and deserialization.
     */
    @Provides
    @Singleton
    fun provideGson() = Gson()
}