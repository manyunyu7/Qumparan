package com.henrylabs.qumparan.view.postdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.henrylabs.qumparan.R
import com.henrylabs.qumparan.data.remote.reqres.PostCommentResponse
import com.henrylabs.qumparan.databinding.ItemCommentBinding
import com.henrylabs.qumparan.databinding.ItemGridRsBinding

class PostCommentAdapter : RecyclerView.Adapter<PostCommentAdapter.PostCommentViewHolder>() {

    val data = mutableListOf<PostCommentResponse.PostCommentResponseItem?>()
    lateinit var adapterInterface: PostItemInterface

    fun setWithNewData(data: MutableList<PostCommentResponse.PostCommentResponseItem?>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun setupAdapterInterface(obj: PostItemInterface) {
        this.adapterInterface = obj
    }

    inner class PostCommentViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemCommentBinding = ItemCommentBinding.bind(itemView)

        fun onBInd(model: PostCommentResponse.PostCommentResponseItem?) {
            val mContext = binding.root.context

            binding.containerComment.animation = AnimationUtils.loadAnimation(
                mContext,
                R.anim.fade_transition_animation
            )

            binding.tvAuthor.text = model?.name.toString()



            binding.root.setOnClickListener {
                adapterInterface.onclick(model)
            }

            binding.tvComment.text = model?.body.toString()
            binding.tvComment.text = model?.email?.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return PostCommentViewHolder(view)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    interface PostItemInterface {
        fun onclick(model: PostCommentResponse.PostCommentResponseItem?)
        fun onUserClick(model: PostCommentResponse.PostCommentResponseItem?)
    }

    override fun onBindViewHolder(holder: PostCommentViewHolder, position: Int) {
        holder.onBInd(data[position])
    }
}