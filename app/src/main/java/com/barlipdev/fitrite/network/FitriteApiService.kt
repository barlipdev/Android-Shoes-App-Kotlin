package com.barlipdev.fitrite.network

import com.barlipdev.fitrite.database.entity.DatabaseBrand
import com.barlipdev.fitrite.network.dto.BrandDTO
import com.barlipdev.fitrite.network.dto.CollectionDTO
import com.barlipdev.fitrite.network.dto.NetworkBrandContainer
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fit-rite.herokuapp.com/api/fitrite/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface FitriteApiService {
    @GET("brands")
    suspend fun getBrandsProperties(): List<BrandDTO>
    @GET("shoes-collection")
    suspend fun getCollectionProperties(): List<CollectionDTO>
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