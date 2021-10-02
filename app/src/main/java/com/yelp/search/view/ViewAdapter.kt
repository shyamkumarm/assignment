package com.yelp.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yelp.search.databinding.ItemLayoutBinding
import com.yelp.search.model.Data
import com.yelp.search.utils.ItemDiffCallback

import kotlinx.android.synthetic.main.item_layout.view.*

class ViewAdapter(
    private val dataList: ArrayList<Data>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val diff by lazy {
        ItemDiffCallback()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(
                ItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).root
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
            (holder as ViewHolder).mView.apply {
                title_view.text = item.title
                body_view.text = item.body
                    }
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(dataNewList: ArrayList<Data>) {
        val diffResult = DiffUtil.calculateDiff(
            diff.apply {
                setList(
                    dataList,
                    dataNewList
                )
            }
        )
        dataList.run {
            clear()
            addAll(dataNewList)
        }
        diffResult.dispatchUpdatesTo(this)
    }
    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)

}
