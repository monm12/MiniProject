package com.HKNU.project.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.HKNU.project.model.SomeInfoDetail
import com.HKNU.project.model.SomeInfoRequest
import com.HKNU.project.network.SomeInfoRepository
import com.HKNU.project.network.onException
import com.HKNU.project.network.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var someInfoRepository: SomeInfoRepository

    //Todo lifecycle owner 가 observe 할 수 있는 데이터 형태.
    // MutableLiveData<[데이터 타입]> = MutableLiveData([기본값])
    // 외부에서는 접근할 수 없도록 private 로 접근 제어 하고, _ 로 시작하는 네이밍을 한다.
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    //Todo 위 private 데이터를 외부에서 접근할 수 있도록 getter 를 제공.
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    //Todo 앱에서 처리하려는 공공 api 응답값의 리스트.
    private val _someInfoList = MutableLiveData<List<SomeInfoDetail>>()
    val someInfoList: LiveData<List<SomeInfoDetail>>
        get() = _someInfoList

    fun requestSomeInfo(
        //Todo 화면에서 전달 받아야 하는 공공 api 요청에 핅요한 값이 있으면 여기를 통해 전달.
    ) {
        val tempList = mutableListOf<SomeInfoDetail>()
        someInfoList.value?.let {
            tempList.addAll(it)
        }

        viewModelScope.launch {
            _isLoading.value = true //통신 중 프로그레스 다이얼로그 처리.
            try {
                //Todo 공공 api 요청 객체.
                val someInfoRequest = SomeInfoRequest(
                    //Todo 공공 api 요청에 필요한 값들을 설정 해 줘야함.
                )
                //Todo 공공 api 요청.
                val result = someInfoRepository.getSomeInfoList(someInfoRequest)
                _isLoading.value = false //통신 중 프로그레스 다이얼로그 처리.

                result.onSuccess {
                    //Todo 공공 api 요청이 성공하여 데이터를 정상적으로 수신하였을 때, 데이터를 처리.
                }.onException {
                    //Todo 공공 api 요청에 실패하였을 때 사용자에게 알림 등 UI 처리 진행.
                }

            } catch (e: Exception) {
                _isLoading.value = false
                Timber.e(e.toString())
                //Todo 통신 실패 등의 경우에 UI 에서 처리 해야할 동작이 가능 하도록..
            }
        }
    }
}