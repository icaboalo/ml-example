package com.icaboalo.mlkitexample.util

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import com.icaboalo.rappiscan.util.FileUtils

/**
 * Created by icaboalo on 02/03/18.
 */
object CameraUtils {


    fun startCamera(context: Activity, requestCode: Int) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtils.getOutputMediaFileUri(context)) // set the image file name
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivityForResult(intent, requestCode)
        }
    }
}