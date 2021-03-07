package com.barlipdev.fitrite.network

import com.barlipdev.fitrite.network.dto.BrandDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fit-rite.herokuapp.com/api/fitrite/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FitriteApiService {
    @GET("brands")
    suspend fun getBrandsProperties(): List<BrandDTO>
}

object FitriteApi {
    val retrofitService : FitriteApiService by lazy {
        retrofit.create(FitriteApiService::class.java)
    }
}