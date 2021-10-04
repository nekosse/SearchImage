package com.example.android.searchimage.search

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.searchimage.databinding.ListViewItemBinding
import com.example.android.searchimage.network.ImageProperty


class ImageListAdapter(var list:ArrayList<ImageProperty>): ListAdapter<ImageProperty, ImageListAdapter.ImagePropertyViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<ImageProperty>() {
        override fun areItemsTheSame(oldItem: ImageProperty, newItem: ImageProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ImageProperty, newItem: ImageProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ImagePropertyViewHolder(private var binding: ListViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {


        fun bind(imageProperty: ImageProperty) {
            binding.property = imageProperty
            binding.executePendingBindings()
        }
        fun getCheckBox():CheckBox{
            return binding.imgCheck
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageListAdapter.ImagePropertyViewHolder {
        return ImagePropertyViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ImageListAdapter.ImagePropertyViewHolder, position: Int) {
        val imageProperty = getItem(position)
        holder.bind(imageProperty)
        holder.getCheckBox().setOnClickListener(){
            if(holder.getCheckBox().isChecked()){
                list.add(imageProperty)
            }
            else{
                list.remove(imageProperty)
            }

        }
    }

}