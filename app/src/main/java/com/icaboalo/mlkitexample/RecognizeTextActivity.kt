
package com.icaboalo.mlkitexample

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.icaboalo.mlkitexample.util.CameraUtils
import com.icaboalo.rappiscan.util.FileUtils
import kotlinx.android.synthetic.main.activity_data.*
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.FirebaseVision
import java.io.File
import java.io.IOException


class RecognizeTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        bt_picture.setOnClickListener {
            CameraUtils.startCamera(this, 200)
        }

        rv_data.layoutManager = LinearLayoutManager(this)
        rv_data.adapter = TextAdapter()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            200 -> {
                if (resultCode == Activity.RESULT_OK) {
                    checkForText(FileUtils.lastFileGeneratedPath)
                }
            }
        }
    }


    fun checkForText(path: String) {
        val image: FirebaseVisionImage
        try {
            image = FirebaseVisionImage.fromFilePath(this, Uri.fromFile(File(path)))
            val detector = FirebaseVision.getInstance()
                    .visionTextDetector
            val result = detector.detectInImage(image)
                    .addOnSuccessListener {
                        (rv_data.adapter as TextAdapter).setData(it.blocks)
                    }
                    .addOnFailureListener {

                    }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
