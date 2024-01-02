package com.HKNU.project.network

import com.HKNU.project.model.SomeInfoRequest
import com.HKNU.project.model.SomeInfoResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

//Todo 사용하려는 공공 api 에 맞춰 수정하여 사용.
// JobInfoService.kt 파일을 참고.
// JobInfoService 와 달리 POST 로 요청하며, SomeInfoRequest 객체를 사용함에 주의.
interface SomeInfoService {

    companion object {
        private const val REQUEST_GET_LIST_URL = "/some/info/list" //rest api url 예시..
    }

    @POST(REQUEST_GET_LIST_URL)
    suspend fun getSomeInfoList(
        @Body request: SomeInfoRequest,
    ): Response<SomeInfoResponse>
}

class SomeInfoServiceImpl @Inject constructor(retrofit: Retrofit) : SomeInfoService {
    private val api by lazy {
        retrofit.create(SomeInfoService::class.java)
    }

    override suspend fun getSomeInfoList(request: SomeInfoRequest): Response<SomeInfoResponse> {
        return api.getSomeInfoList(request)
    }
}