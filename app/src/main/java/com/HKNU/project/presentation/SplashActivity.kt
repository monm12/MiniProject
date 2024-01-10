package com.HKNU.project.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.HKNU.project.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity @Inject constructor() : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        mContext = this@SplashActivity
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Todo 여기서 레이아웃에 대한 처리와 앱 구동 시 수행 되어야 하는 작업들을 정의 하세요.
        // 샘플은 3초가 기다린 후에 메인 화면으로 이동합니다.
        moveToMain()
    }

    private fun moveToMain() {
        CoroutineScope(Dispatchers.Main).launch {
            //3초간 정지.
            delay(3_000)

            //메인 화면 호출.
            MainActivity.start(this@SplashActivity)

            //Splash 화면은 종료.
            finish()
        }
    }
}