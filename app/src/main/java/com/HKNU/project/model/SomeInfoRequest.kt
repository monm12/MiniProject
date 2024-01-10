package com.HKNU.project.model

import com.google.gson.annotations.SerializedName
import com.HKNU.project.common.AppConstant
import com.HKNU.project.extensions.empty

data class SomeInfoRequest(
    // 필수
    @SerializedName("ServiceKey")
    val serviceKey: String = AppConstant.API_AUTH_KEY,

    //요청 변수 추가...

    // 필수
    @SerializedName("Page")
    val pageNum: Int = 1,

    // 옵션
    @SerializedName("Org")
    val orgName: String = String.empty(),

    @SerializedName("Mobile")
    val mobile: Int = 1,
)
