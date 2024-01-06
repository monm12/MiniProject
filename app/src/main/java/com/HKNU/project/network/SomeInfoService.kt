package com.HKNU.project.network

import com.HKNU.project.common.AppConstant
import com.HKNU.project.model.SomeInfoRequest
import com.HKNU.project.model.SomeInfoResponse
import com.HKNU.project.extensions.empty
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Inject

//Todo 사용하려는 공공 api 에 맞춰 수정하여 사용.
// JobInfoService.kt 파일을 참고.
// JobInfoService 와 달리 POST 로 요청하며, SomeInfoRequest 객체를 사용함에 주의.
interface SomeInfoService {

    companion object {
        private const val REQUEST_GET_LIST_URL = "http://apis.data.go.kr/B552881/kmooc/" //rest api url 예시..
    }

    @GET("/$REQUEST_GET_LIST_URL/{pageNum} ")
    suspend fun getSomeInfoList(
        //@Body request: Int,
        @Path("pageNum") pageNum : Int,
    ): Response<SomeInfoResponse>
}

class SomeInfoServiceImpl @Inject constructor(retrofit: Retrofit) : SomeInfoService {
    private val api by lazy {
        retrofit.create(SomeInfoService::class.java)
    }

    override suspend fun getSomeInfoList(pageNum: Int): Response<SomeInfoResponse> {
        return api.getSomeInfoList(pageNum)
    }
}