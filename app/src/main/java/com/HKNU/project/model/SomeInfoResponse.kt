package com.HKNU.project.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//Todo 사용하려는 공공 api 에 맞춰 수정하여 사용.
// JobInfoResponse.kt 파일을 참고.
data class SomeInfoResponse(

    //Todo 예시 응답 값..
    @SerializedName("list_total_count")
    val listTotalCount: Int,

    @SerializedName("list")
    val someInfoDetailList: List<SomeInfoDetail>,

    //Todo 여기에 데이터들을 추가..
)

@Parcelize
data class SomeInfoDetail(

    //Todo 첫번째 데이터 예시.
    @SerializedName("some_first_info")
    val someFirstInfo: String,

    //Todo 두번째 데이터 예시.
    @SerializedName("some_second_info")
    val someSecondInfo: String,

    //Todo 여기에 데이터들을 추가..
) : Parcelable