package com.henrylabs.qumparan.view.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.henrylabs.qumparan.R
import com.henrylabs.qumparan.data.remote.reqres.UserAlbumResponse
import com.henrylabs.qumparan.databinding.ItemAlbumBinding

class UserAlbumAdapter : RecyclerView.Adapter<UserAlbumAdapter.UserAlbumViewHolder>() {

    val data = mutableListOf<UserAlbumResponse.UserAlbumResponseItem?>()
    lateinit var adapterInterface: AlbumItemInterface

    fun setWithNewData(data: MutableList<UserAlbumResponse.UserAlbumResponseItem?>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun setupAdapterInterface(obj: AlbumItemInterface) {
        this.adapterInterface = obj
    }

    inner class UserAlbumViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemAlbumBinding = ItemAlbumBinding.bind(itemView)

        fun onBInd(model: UserAlbumResponse.UserAlbumResponseItem?) {
            val mContext = binding.root.context

            binding.containerComment.animation = AnimationUtils.loadAnimation(
                mContext,
                R.anim.fade_transition_animation
            )

            binding.tvAlbumName.text = model?.title.toString()


            binding.root.setOnClickListener {
                adapterInterface.onclick(model)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return UserAlbumViewHolder(view)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    interface AlbumItemInterface {
        fun onclick(model: UserAlbumResponse.UserAlbumResponseItem?)
    }

    override fun onBindViewHolder(holder: UserAlbumViewHolder, position: Int) {
        holder.onBInd(data[position])
    }
}