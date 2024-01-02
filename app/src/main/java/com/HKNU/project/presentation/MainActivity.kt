package com.HKNU.project.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.HKNU.project.R

class MainActivity : AppCompatActivity() {
    // API 키 받기
    private lateinit var edit : EditText
    private  lateinit var  text : TextView
    private var key = "xZVyEXB5g2v3CEyT4QnoWdABt2ZHH4jLNuEHT2R7ivPWW6oBFrBUDOdw6mAt9dohXYqIpHc5SPlF5pmgRZ2dFg%3D%3D"
    private var data : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}

