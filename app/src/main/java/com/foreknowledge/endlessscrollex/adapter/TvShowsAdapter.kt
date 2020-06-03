package com.foreknowledge.endlessscrollex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.foreknowledge.endlessscrollex.databinding.ItemTvShowBinding
import com.foreknowledge.endlessscrollex.listener.ItemClickListener
import com.foreknowledge.endlessscrollex.network.TvShow

/**
 * Create by Yeji on 03,June,2020.
 */
class TvShowsAdapter : PagedListAdapter<TvShow, TvShowsAdapter.TvShowsViewHolder>(diffCallback) {

    private var onClickListener: ItemClickListener<TvShow> = object: ItemClickListener<TvShow> {
        override fun onItemClick(item: TvShow) {
            // do nothing
        }
    }

    fun setItemClickListener(onClick: (item: TvShow) -> Unit) {
        onClickListener = object : ItemClickListener<TvShow> {
            override fun onItemClick(item: TvShow) {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder = TvShowsViewHolder(
        ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class TvShowsViewHolder(private val binding: ItemTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TvShow?) {
            binding.item = item
            binding.tvShowLayout.setOnClickListener {
                if (item != null) {
                    onClickListener.onItemClick(item)
                }
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                oldItem == newItem
        }
    }
}