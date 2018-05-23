package com.icaboalo.mlkitexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.ml.vision.text.FirebaseVisionText
import kotlinx.android.synthetic.main.view_item_data.view.*

class TextAdapter: RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    val data: ArrayList<FirebaseVisionText.Block> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_data, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(blocks: List<FirebaseVisionText.Block>) {
        data.clear()
        data.addAll(blocks)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(block: FirebaseVisionText.Block) {
            itemView.tv_label.text = block.text
        }
    }
}