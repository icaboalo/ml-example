package com.icaboalo.mlkitexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmark
import kotlinx.android.synthetic.main.view_item_data.view.*

class LandmarkAdapter: RecyclerView.Adapter<LandmarkAdapter.LandmarkViewHolder>() {

    val data: ArrayList<FirebaseVisionCloudLandmark> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_data, parent, false)

        return LandmarkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: LandmarkViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun setData(list: List<FirebaseVisionCloudLandmark>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class LandmarkViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(landmark: FirebaseVisionCloudLandmark) {
            itemView.tv_label.text = "Landmark: ${landmark.landmark}"
            itemView.tv_id.text = "Id: ${landmark.entityId}"
            itemView.tv_confidence.text = "Confidence: ${landmark.confidence}"
        }
    }

}
