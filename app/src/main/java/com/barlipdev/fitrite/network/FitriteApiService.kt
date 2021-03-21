package com.barlipdev.fitrite.network

import com.barlipdev.fitrite.network.dto.BrandDTO
import com.barlipdev.fitrite.network.dto.CollectionDTO
import com.barlipdev.fitrite.network.dto.ShoeDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://fit-rite.herokuapp.com/api/fitrite/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface FitriteApiService {
    @GET("brands")
    suspend fun getBrandsProperties(): List<BrandDTO>
    @GET("shoes-collection")
    suspend fun getCollectionProperties(): List<CollectionDTO>
    @GET("shoes/brands/{idBrand}")
    suspend fun getShoesByBrandId(@Path("idBrand") idBrand: String): List<ShoeDTO>
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object FitriteApi {

    val retrofitService : FitriteApiService by lazy {
        retrofit.create(FitriteApiService::class.java)
    }
}