package com.HKNU.project.presentation

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.HKNU.project.databinding.ActivityDetailBinding
import com.HKNU.project.model.SomeInfoDetail
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity @Inject constructor() : AppCompatActivity() {

    companion object {
        private const val EXTRA_DETAIL_DATA = "EXTRA_DETAIL_DATA"

        //Todo 상세 화면에서 표시할 '상세 정보 데이터'를 이전 화면에서 전달 받아 사용한다. SomeInfoDetail.
        fun start(activity: Activity, detail: SomeInfoDetail) {
            Intent(activity, DetailActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                putExtra(EXTRA_DETAIL_DATA, detail)
            }.also {
                activity.startActivity(it)
            }
        }
    }

    private lateinit var mContext: Context
    private lateinit var binding: ActivityDetailBinding

    private val someInfoDetail: SomeInfoDetail? by lazy {
        intent.getParcelableExtra(EXTRA_DETAIL_DATA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this@DetailActivity
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Todo 전달 받은 상세 정도 데이터가 null 이면, 사용자에게 얼럿을 보여주고, DetailActivity 를 종료 합니다.
        if (someInfoDetail == null) {
            AlertDialog.Builder(mContext)
                .setTitle("죄송합니다")
                .setMessage("데이터를 제대로 가져오지 못했습니다.")
                .setPositiveButton("확인") { _, _ ->
                    finish()
                }
                .show()
        } else {
            setDataToUI()
        }
    }

    //Todo 데이터를 UI 와 연결하세요.
    private fun setDataToUI() {
        binding.detailEx.text = someInfoDetail?.blocksUrl
    }
}

