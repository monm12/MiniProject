package com.HKNU.project.network;

import com.google.gson.annotations.SerializedName

//Todo '서울시 일자리플러스센터 채용 정보' 공공 api 응답의 최상위 객체를 위한 데이터 클래스.
// 사용하려는 공공 api 에 따라 사용이 불필요할 수도 있고, 형태가 다를 수도 있음.
// SampleApiInfo 파일의 응답 데이터 샘플의 형태를 보면, 이 클래스의 역할이 이해가 쉬움.

data class BaseResponse<out T>(
        @SerializedName("GetJobInfo")
        val info: T?
)
