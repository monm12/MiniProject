package com.HKNU.project.extensions

import android.app.NotificationManager
import android.content.Context
import android.hardware.SensorManager
import android.net.ConnectivityManager

//Todo Kotlin 의 확장 기능을 정의한 파일. 필요한 기능들을 확장하여 사용.

fun String.Companion.appName() ="HKNU"
fun String.Companion.empty() = ""

fun Context.isNetworkEnable(): Boolean {
    var isEnable = false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    isEnable = null != activeNetwork
    return isEnable
}

val Context.sensorManager: SensorManager
    get() = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager

val Context.notificationManager: NotificationManager
    get() = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager