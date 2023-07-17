package com.test.contactapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.contactapp.data.service.ContactApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provedBaseUrl() : String = "https://reqres.in/api"


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideGson (): Gson = GsonBuilder()
        .setLenient()
        .create();

    @Provides
    @Singleton
    fun provideRetrofitBuilder(baseUrl: String,  okHttpClient: OkHttpClient, gson: Gson ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()



    @Provides
    fun providesContactApi( retrofit: Retrofit): ContactApi =
        retrofit.create(ContactApi::class.java)

}
