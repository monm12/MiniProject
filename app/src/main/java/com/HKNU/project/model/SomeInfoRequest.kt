package com.HKNU.project.model

import com.google.gson.annotations.SerializedName
import com.HKNU.project.common.AppConstant

//Todo 사용하려는 공공 api 에 맞춰 수정하여 사용.
// JobInfoRequest.kt 파일을 참고.
data class SomeInfoRequest(
    // 필수
    @SerializedName("ServiceKey") //api 명세에 정의된 파라메터 명.
    val authKey: String = AppConstant.API_AUTH_KEY, //앱에서 사용 하려는 변수명.

    //요청 변수 추가...
    // 필수
    @SerializedName("Page")
    val pageNum : Int = 1,

    // 옵션
    //@SerializedName("Org")
    //val orgName : String =

)
