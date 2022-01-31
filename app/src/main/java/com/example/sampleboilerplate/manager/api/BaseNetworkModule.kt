package com.example.sampleboilerplate.manager.api

import android.content.Context
import com.example.sampleboilerplate.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.reflect.Field
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseNetworkModule {

    //const val ANNOTATION_BASE_VERDANDI = "verdandi"
    const val ANNOTATION_BASE_LOKI = "loki"
    const val ANNOTATION_BASE_JORD = "jord"


    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setFieldNamingStrategy { f: Field ->
                f.name.lowercase()
            }
            .serializeNulls()
            .setLenient()
            .create()


    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ) : OkHttpClient =
        OkHttpClient.Builder().apply {
           //INFO add timer, interceptor, etc
        }.build()



    //info assumed verdandi
    @Provides
    @Singleton
    fun provideBaseUrl(): String =
       BuildConfig.APPLICATION_ID



}