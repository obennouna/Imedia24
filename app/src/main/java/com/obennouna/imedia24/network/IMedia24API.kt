package com.obennouna.imedia24.network

import com.obennouna.imedia24.pojo.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface IMedia24API {

    @Headers(
        "Accept: application/json",
        "appDevice: ANDROID_PHONE",
        "locale: de_DE"
    )
    @GET("/ext-api/app/1/category/tree")
    suspend fun getTreeCategory(): Response<Category>

    @Headers(
        "Accept: application/json",
        "appDevice: ANDROID_PHONE",
        "locale: de_DE"
    )
    @GET("/ext-api/app/1/c/**/*-{categoryId}")
    suspend fun getProductsByCategory(@Path(value = "categoryId") categoryId: Int): Response<Category>

    @Headers(
        "Accept: application/json",
        "appDevice: ANDROID_PHONE",
        "locale: de_DE"
    )
    @GET("/ext-api/app/1/product/{sku}")
    suspend fun getProductByCategory(@Path(value = "sku") sku: Int): Response<Category>

}