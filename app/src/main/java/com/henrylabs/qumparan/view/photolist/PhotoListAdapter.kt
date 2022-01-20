package com.henrylabs.qumparan.view.photolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.henrylabs.qumparan.R
import com.henrylabs.qumparan.data.remote.reqres.AlbumPhotoResponse
import com.henrylabs.qumparan.data.remote.reqres.PostCommentResponse
import com.henrylabs.qumparan.databinding.ItemCommentBinding
import com.henrylabs.qumparan.databinding.ItemPhotoBinding

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.PhotoListViewHolder>() {

    val data = mutableListOf<AlbumPhotoResponse.AlbumPhotoResponseItem?>()
    lateinit var adapterInterface: PostItemInterface

    fun setWithNewData(data: MutableList<AlbumPhotoResponse.AlbumPhotoResponseItem?>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun setupAdapterInterface(obj: PostItemInterface) {
        this.adapterInterface = obj
    }

    inner class PhotoListViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemPhotoBinding = ItemPhotoBinding.bind(itemView)

        fun onBInd(model: AlbumPhotoResponse.AlbumPhotoResponseItem?) {
            val mContext = binding.root.context

            binding.base.animation = AnimationUtils.loadAnimation(
                mContext,
                R.anim.fade_transition_animation
            )

            Glide.with(mContext)
                .load(model?.url.toString()+".png")
                .thumbnail(Glide.with(mContext).load(R.raw.ic_loading_google).fitCenter())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.photo)


            binding.tvAuthor.text = model?.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoListViewHolder(view)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    interface PostItemInterface {
        fun onclick(model: AlbumPhotoResponse.AlbumPhotoResponseItem?)
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        holder.onBInd(data[position])
    }
}