package com.icaboalo.mlkitexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.ml.vision.label.FirebaseVisionLabel
import kotlinx.android.synthetic.main.view_item_data.view.*

class ImageLabelAdapter: RecyclerView.Adapter<ImageLabelAdapter.DataViewHolder>() {

    private val data: ArrayList<FirebaseVisionLabel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_data, parent, false)

        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(labels: List<FirebaseVisionLabel>) {
        data.clear()
        data.addAll(labels)

        notifyDataSetChanged()
    }

    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(label: FirebaseVisionLabel) {
            itemView.tv_label.text = "Label: ${label.label}"
            itemView.tv_confidence.text = "Confidence: ${label.confidence}"
            itemView.tv_id.text = "Id: ${label.entityId}"
        }
    }
}