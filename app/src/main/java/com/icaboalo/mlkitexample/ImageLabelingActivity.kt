package com.icaboalo.mlkitexample

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.icaboalo.mlkitexample.util.CameraUtils
import com.icaboalo.rappiscan.util.FileUtils
import kotlinx.android.synthetic.main.activity_data.*
import java.io.File
import java.io.IOException


class ImageLabelingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        FirebaseApp.initializeApp(this)

        bt_picture.setOnClickListener {
            CameraUtils.startCamera(this, 200)
        }

        rv_data.layoutManager = LinearLayoutManager(this)
        rv_data.adapter = ImageLabelAdapter()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            200 -> {
                if (resultCode == Activity.RESULT_OK) {
                    checkLabels(FileUtils.lastFileGeneratedPath)
                }
            }
        }
    }

    fun checkLabels(path: String) {
        val image: FirebaseVisionImage
        try {
            image = FirebaseVisionImage.fromFilePath(this, Uri.fromFile(File(path)))

            val detector = FirebaseVision.getInstance()
                    .visionLabelDetector


            val result = detector.detectInImage(image)
                    .addOnSuccessListener {
                        // Task completed successfully
                        // ...
                        for (label in it) {
                            val text = label.label
                            val entityId = label.entityId
                            val confidence = label.confidence

                            Log.d("label", "$text / $entityId / $confidence")
                        }

                        (rv_data.adapter as ImageLabelAdapter).setData(it)
                    }
                    .addOnFailureListener {
                        // Task failed with an exception
                        // ...
                    }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
