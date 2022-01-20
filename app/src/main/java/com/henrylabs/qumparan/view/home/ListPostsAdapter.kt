package com.henrylabs.qumparan.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henrylabs.qumparan.R
import com.henrylabs.qumparan.databinding.ItemGridRsBinding
import com.henrylabs.qumparan.view.home.ListPostsAdapter.PostViewHolder

class ListPostsAdapter : RecyclerView.Adapter<PostViewHolder>() {

    val data = mutableListOf<ShowedPosts?>()
    lateinit var adapterInterface: PostItemInterface

    fun setWithNewData(data: MutableList<ShowedPosts?>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun setupAdapterInterface(obj: PostItemInterface) {
        this.adapterInterface = obj
    }

    inner class PostViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemGridRsBinding = ItemGridRsBinding.bind(itemView)

        fun onBInd(model: ShowedPosts?) {
            val mContext = binding.root.context

            binding.root.setOnClickListener {
                adapterInterface.onclick(model)
            }

            binding.tvPost.text = model?.body.toString()

            binding.tvAuthor.title(mContext.getString(R.string.written_by))
            binding.tvAuthor.value(model?.userName.toString())

            binding.tvTitle.text = model?.title.toString()
            binding.tvnumber.text = (adapterPosition + 1).toString()

            binding.tvCompany.build(
                mContext.getString(R.string.company_by),
                model?.userCompanyName.toString()
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_rs, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBInd(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface PostItemInterface {
        fun onclick(model: ShowedPosts?)
        fun onUserClick(model: ShowedPosts?)
    }
}