package com.HKNU.project.network

import com.HKNU.project.model.SomeInfoResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface SomeInfoService {

    companion object {
        private const val REQUEST_GET_LIST_URL = "courseList" //rest api url 예시..
    }

    @GET(REQUEST_GET_LIST_URL)
    suspend fun getSomeInfoList(
        @Query("ServiceKey", encoded = true) key: String,
        @Query("Page", encoded = true) page: Int,
        @Query("Org", encoded = true) org: String,
        @Query("Mobile", encoded = true) mobile: Int,
    ): Response<SomeInfoResponse>
}

class SomeInfoServiceImpl @Inject constructor(retrofit: Retrofit) : SomeInfoService {
    private val api by lazy {
        retrofit.create(SomeInfoService::class.java)
    }

    override suspend fun getSomeInfoList(
        key: String,
        page: Int,
        org: String,
        mobile: Int,
    ): Response<SomeInfoResponse> {
        return api.getSomeInfoList(key, page, org, mobile)
    }
}