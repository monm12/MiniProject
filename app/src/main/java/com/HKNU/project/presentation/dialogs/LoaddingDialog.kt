package com.HKNU.project.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.HKNU.project.R
import com.HKNU.project.databinding.DialogLoadingBinding
import com.HKNU.project.presentation.MainViewModel

//Todo 로딩 다이얼로그. 로딩 시 노출되는 애니메이션만 바꾼다면 거의 그대로 사용 가능.
class LoadingDialog : DialogFragment() {
    companion object {
        const val TAG = "LoadingDialog"

        fun newInstance() = LoadingDialog()
    }

    private lateinit var binding: DialogLoadingBinding
    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.MyDialogTheme)
        isCancelable = false

        viewModel.isLoading.observe(this) { loading ->
            if (!loading) {
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Code some stub..
    }
}