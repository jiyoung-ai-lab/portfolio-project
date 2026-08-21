package com.waveapp.tourcat.helper

import android.graphics.Bitmap
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/*
 현재 사용하지 않는 영역 (Openai 자체 오류로 image_url 처리 못함...
 */
object FileUploadHelper {

    private const val PRESIGNED_API_URL = "https://ssnd42yebivh42y6rwag3wxkni0dedcm.lambda-url.us-east-1.on.aws/"   // 람다

    private val client = OkHttpClient()

    /**
     * 비트맵을 파일로 변환 후 Presigned URL로 S3 업로드
     * - upload_url: PUT 업로드용
     * - view_url: GPT/OpenAI에서 GET 다운로드용
     */
    fun uploadBitmap(
        bitmap: Bitmap,
        fileName: String,
        callback: (fileUrl: String?, error: String?) -> Unit
    ) {
        try {
            // 1. 비트맵을 JPEG 파일로 임시 저장
            val tempFile = File.createTempFile("upload_", ".jpg")
            FileOutputStream(tempFile).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            }

            // 2. Presigned URL 요청
            val presignedRequest = Request.Builder()
                .url("$PRESIGNED_API_URL?filename=$fileName")
                .get()
                .build()

            client.newCall(presignedRequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    callback(null, "Presigned URL 요청 실패: ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyStr = response.body?.string()
                    if (!response.isSuccessful || bodyStr.isNullOrEmpty()) {
                        callback(null, "Presigned URL 서버 오류: ${response.code}")
                        return
                    }

                    val json = JSONObject(bodyStr)
                    val uploadUrl = json.optString("upload_url", null)
                    val viewUrl = json.optString("view_url", null)

                    if (uploadUrl == null || viewUrl == null) {
                        callback(null, "Presigned URL 생성 실패")
                        return
                    }

                    // 3. Presigned URL로 S3 업로드 (PUT 방식)
                    val requestBody = tempFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
                    val uploadRequest = Request.Builder()
                        .url(uploadUrl)
                        .put(requestBody)
                        .build()

                    client.newCall(uploadRequest).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            callback(null, "S3 업로드 실패: ${e.message}")
                        }

                        override fun onResponse(call: Call, response: Response) {
                            if (response.isSuccessful) {
                                // GPT에 전달할 view_url 반환
                                callback(viewUrl, null)
                            } else {
                                callback(null, "S3 응답 오류: ${response.code}")
                            }
                        }
                    })
                }
            })

        } catch (e: Exception) {
            callback(null, "파일 생성 실패: ${e.message}")
        }
    }
}
