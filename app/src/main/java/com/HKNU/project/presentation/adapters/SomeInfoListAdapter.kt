package com.HKNU.project.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.HKNU.project.databinding.RowSomeInfoBinding
import com.HKNU.project.model.SomeInfoDetail

//Todo 리스트어덥터 샘플. 리스트를 사용하는 경우, MainActivity 와 함께 사용 예시를 확인.
// 데이터 타입과 레이아웃 관련 코드만 바꾸면 큰 수정 없이 사용 가능.
class SomeInfoListAdapter : ListAdapter<SomeInfoDetail, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<SomeInfoDetail>() {
        override fun areItemsTheSame(oldItem: SomeInfoDetail, newItem: SomeInfoDetail): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: SomeInfoDetail, newItem: SomeInfoDetail): Boolean =
            true
    }
) {

    var onItemClickListener: (SomeInfoDetail) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SomeInfoViewHolder(
            RowSomeInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SomeInfoViewHolder).bind(getItem(position))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitListAndScroll(list: List<SomeInfoDetail>?, needScroll: (Boolean) -> Unit) {
        val isAdded = itemCount < (list?.size ?: 0)
        super.submitList(list) { notifyDataSetChanged() }
        needScroll.invoke(isAdded)
    }

    inner class SomeInfoViewHolder(private val binding: RowSomeInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: SomeInfoDetail) {
            binding.root.setOnClickListener {
                onItemClickListener(data)
            }

            //Todo 리스트 아이템의 view 에 data 를 셋팅하는 예시임. 실제 구현 시에는 삭제.
            binding.tvSample.text = data.blocksUrl

        }
    }
}