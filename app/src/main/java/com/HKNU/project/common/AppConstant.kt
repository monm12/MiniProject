package com.HKNU.project.common

import com.HKNU.project.BuildConfig

object AppConstant {

    val isDebug = BuildConfig.DEBUG

    const val API_REAL = "http://apis.data.go.kr/B552881/kmooc/courseList" //Todo 운영용 api 요청 url. base url. ex)http://openapi.seoul.go.kr:8088
    const val API_DEV = "http://apis.data.go.kr/B552881/kmooc" //Todo 개발용 api 요청 url. base url. ex)http://dev.openapi.seoul.go.kr:8088
    val API_HOST = if (isDebug) API_DEV else API_REAL //Todo 개발과 운영 서버가 나눠져 있지 않다면 REAL 과 DEV 가 동일.

    const val API_AUTH_KEY = "xZVyEXB5g2v3CEyT4QnoWdABt2ZHH4jLNuEHT2R7ivPWW6oBFrBUDOdw6mAt9dohXYqIpHc5SPlF5pmgRZ2dFg%3D%3D" //Todo 공공 api 사용을 위한 인증 키. 공공 api 사용 사이트에서 발급. ex)52454256736a62738133304d7962756e

    //int code
    const val REQ_CODE_PROPERTIES = 750_001

    //notification related //Comm 알림 영역에서 사용할 값들. 알림을 사용하는 경우라면 필요.

    const val NOTIFICATION_CHANNEL_ID = "${BuildConfig.APPLICATION_ID}.notification.channel"
    const val NOTIFICATION_ID_DEFAULT = 850_001
    const val NOTIFICATION_ID_STEPS = 850_002

    //string keys //Comm 프로젝트의 여러 곳에서 사용될 '문자열'키. 아래는 예시 코드.
    const val STR_KEY_SOME = "key.some"
    const val STR_USER_ID = "pref.user.id" //
    const val STR_USER_NAME = "pref.user.name"


}