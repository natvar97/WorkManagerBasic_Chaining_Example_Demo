package com.indialone.workmanagerbasic_chaining_example_demo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class CropWorker(context: Context , params : WorkerParameters) : Worker(context , params) {
    override fun doWork(): Result {
        makeStatusNotification("Cropping selected" , applicationContext)
        Thread.sleep(2000)
        Log.e("cropping" ,"Cropping is running to the image...")
        return Result.success()
    }
}