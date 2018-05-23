package com.icaboalo.mlkitexample

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmark
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.icaboalo.mlkitexample.util.CameraUtils
import com.icaboalo.rappiscan.util.FileUtils
import kotlinx.android.synthetic.main.activity_data.*
import java.io.File
import java.io.IOException

class LandmarkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        bt_picture.setOnClickListener {
            CameraUtils.startCamera(this, 200)
        }

        rv_data.layoutManager = LinearLayoutManager(this)
        rv_data.adapter = LandmarkAdapter()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            200 -> {
                if (resultCode == Activity.RESULT_OK) {
                    checkLandmark(FileUtils.lastFileGeneratedPath)
                }
            }
        }
    }

    fun checkLandmark(path: String) {
        try {
            val image = FirebaseVisionImage.fromFilePath(this, Uri.fromFile(File(path)))
            val detector = FirebaseVision.getInstance().visionCloudLandmarkDetector

            val result = detector.detectInImage(image)
                    .addOnSuccessListener {
                        (rv_data.adapter as LandmarkAdapter).setData(it)

                        Log.d("landmark", "landmakrs")
                        for (label in it) {
                            val text = label.landmark
                            val entityId = label.entityId
                            val confidence = label.confidence

                            Log.d("landmark", "$text / $entityId / $confidence")
                        }
                    }

        } catch (e: IOException) {
            Log.e("landmark", "Error", e)
        }
    }
}
