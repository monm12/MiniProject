package com.HKNU.project.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//Todo 사용하려는 공공 api 에 맞춰 수정하여 사용.
// JobInfoResponse.kt 파일을 참고.

// CourseInfo.kt
data class SomeInfoResponse(

    //Todo 예시 응답 값..
    @SerializedName("pagination")
    val pageDetail: PageDetail,

    @SerializedName("results")
    val someInfoDetailList: List<SomeInfoDetail>

    //Todo 여기에 데이터들을 추가..
)

data class PageDetail(
    @SerializedName("count")
    val count: Int,
    @SerializedName("num_pages")
    val numPages: Int
)

@Parcelize
data class SomeInfoDetail(
    //Todo 첫번째 데이터 예시.

    // 강좌 url
    @SerializedName("blocks_url")
    val blocksUrl: String,

    //Todo 두번째 데이터 예시.

    // 강좌 주요시간
    @SerializedName("effort")
    val effort: String,

    //Todo 여기에 데이터들을 추가..

    // 강좌 종료일
    @SerializedName("End")
    val end: String,

    // 수강신청 시작일
    @SerializedName("enrollment_start")
    val senrollmentStart: String,

    // 수강신청 종료일
    @SerializedName("enrollment_end")
    val senrollmentEnd: String,

    // 강좌 아이디
    @SerializedName("id")
    val id: String,

    // 강좌 이미지
    @SerializedName("course_image")
    val courseImage: String,

    // 강좌명
    @SerializedName("name")
    val name: String,

    // 강좌번호
    @SerializedName("Number")
    val number: String,

    // 기관명
    @SerializedName("Org")
    val orgName: String,

    // 짧은 소개
    @SerializedName("short_description")
    val shortDescription: String,

    // 강좌 시작일 YY-MM-DD
    @SerializedName("start")
    val courseStart: String,

    // 강좌 시작일 - 년/월/일
    @SerializedName("start_display")
    val courseStartDisplay: String,

    // 강좌 시작일 표시 형식
    @SerializedName("start_type")
    val courseStartType: String,

    // 강좌 형식
    @SerializedName("Pacing")
    val pacing: String,

    // 모바일 지원여부
    @SerializedName("mobile_available")
    val mobileAvailable: String,

    // 강좌 감춤여부
    @SerializedName("hidden")
    val hidden: String,

    ) : Parcelable