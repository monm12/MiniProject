package com.HKNU.project.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.HKNU.project.databinding.ActivityListBinding
import com.HKNU.project.model.SomeInfoDetail
import com.HKNU.project.presentation.adapters.SomeInfoListAdapter
import com.HKNU.project.presentation.dialogs.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity @Inject constructor() : AppCompatActivity() {

    companion object {
        fun start(activity: Activity, someInfoList: SomeInfoDetail) {
            Intent(activity, ListActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }.also {
                activity.startActivity(it)
            }
        }
    }

    private lateinit var mContext: Context
    private lateinit var binding: ActivityListBinding //레이아웃. xml 파일.

    private val viewModel: MainViewModel by viewModels() //viewModel.

    //Todo 리스트를 사용하는 경우 리스트의 데이터 처리를 담당하는 어덥터 예시.
    private val listAdapter by lazy {
        SomeInfoListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this@ListActivity
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Todo 리스트뷰에 데이터를 담당하는 어덥터를 셋팅하고, 아이템 클릭이 발생했을 때 처리를 설정.
        binding.recycleList.apply {
            adapter = listAdapter.apply {
                //Todo 리스트 아이템 클릭이 발생했을 때 동작 리스너.
                onItemClickListener = { someInfoDetail ->
                    //Todo 예제에서는 DetailActivit 를 호출.
                    DetailActivity.start(this@ListActivity, someInfoDetail)
                }
            }
        }

        //Todo 버튼 클릭 이벤트에 처리 예시. 샘플에서는 공공 api 데이터를 요청한다.
        binding.reserchButton.setOnClickListener {
            executeQuery()
        }

        //Todo 변경 시 UI 에서 알아야 하는 데이터들에 대한 처리를 설정.
        setObservers()

    }

    private fun setObservers() {
        //Todo 네트워크 통신 요청 시 사용자에게 프로그레스를 보여줌.
        viewModel.isLoading.observe(this) { loading ->
            //Todo loading 이 true 이면 로딩 화면을 노출.
            // loading 이 false 로 바뀌면 LoadingDialog 에서 observe 하고 있다가 자신이 dismiss 됨.
            if (loading) {
                LoadingDialog.newInstance()
                    .show(supportFragmentManager, LoadingDialog.TAG)
            }
        }

        //Todo 리스트의 데이터가 변경되는 것에 대한 처리.
        viewModel.someInfoList.observe(this) { someInfoList ->
            listAdapter.run {
                submitListAndScroll(someInfoList) {
                    binding.recycleList.smoothScrollToPosition(this.itemCount)
                }
            }
        }

        //Todo 그외 LiveData 로 구현하여 감시하고 있어야하는 데이터들에 대한 처리를 수행..
    }

    //Todo 공공 api 요청.
    private fun executeQuery() {
        //Todo 공공 api 요청에 필요한 인자 중 화면을 통해 사용자로부터 받은 데이터가 있으면
        // 아래 함수 호출 시 인자로 넘겨주어 처리한다.
        viewModel.requestSomeInfo(pageNum = 1)
    }

}

