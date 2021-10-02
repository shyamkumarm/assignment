package com.yelp.search.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil

class ItemDiffCallback : DiffUtil.Callback() {
    private lateinit var oldList: List<Businesse>
    private lateinit var newList: List<Businesse>
    fun setList(oldList: List<Businesse>, newList: List<Businesse>){
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id === newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {


        return oldList[oldPosition].name == newList[newPosition].name && oldList[oldPosition].image_url == newList[newPosition].image_url
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}