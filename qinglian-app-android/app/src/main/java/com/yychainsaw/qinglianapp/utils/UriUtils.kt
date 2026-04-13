package com.yychainsaw.qinglianapp.utils

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

object UriUtils {
    // 将 Uri 转换为 MultipartBody.Part
    fun prepareFilePart(context: Context, fileUri: Uri, paramName: String = "file"): MultipartBody.Part? {
        val contentResolver = context.contentResolver
        
        // 1. 创建临时文件
        val tempFile = File(context.cacheDir, "upload_temp_${System.currentTimeMillis()}.jpg")
        
        try {
            // 2. 将 Uri 的输入流复制到临时文件
            val inputStream = contentResolver.openInputStream(fileUri) ?: return null
            val outputStream = FileOutputStream(tempFile)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()

            // 3. 创建 RequestBody
            // 假设上传的是图片，使用 image/* 类型
            val requestFile = tempFile.asRequestBody("image/jpeg".toMediaTypeOrNull())

            // 4. 创建 MultipartBody.Part
            return MultipartBody.Part.createFormData(paramName, tempFile.name, requestFile)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
