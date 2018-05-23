package com.icaboalo.rappiscan.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.support.v4.content.FileProvider
import java.io.File

/**
 * Created by icaboalo on 02/03/18.
 */
object FileUtils {

    var lastFileGeneratedPath: String = ""

    fun getOutputMediaFileUri(context: Activity): Uri {
        return FileProvider.getUriForFile(context, "com.icaboalo.mlkitexample.provider", getOutputMediaFile())
    }

    private fun getOutputMediaFile(): File {

        val mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Example")
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return File("")
            }
        }

        // Create a media file name
        val mediaFile: File
        mediaFile = File(mediaStorageDir.path + File.separator + "IMG_example.jpg")
        lastFileGeneratedPath = mediaFile.absolutePath


        return mediaFile
    }
}