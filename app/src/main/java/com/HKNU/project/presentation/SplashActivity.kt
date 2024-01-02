package com.HKNU.project.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity @Inject constructor() : AppCompatActivity() {

    private lateinit var mContext : Context
    //private lateinit var binding : ActivitySlashBinding

    //override fun OnCreate(savedInstanceState : Bundle?){

    }


}