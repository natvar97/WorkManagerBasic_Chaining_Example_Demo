package com.indialone.workmanagerbasic_chaining_example_demo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ResizeWorker(context: Context , params : WorkerParameters) : Worker(context , params) {
    override fun doWork(): Result {
        makeStatusNotification("Resizing image is started..." , applicationContext)
        Thread.sleep(2000)
        Log.e("resizing", "Resizing the image is started...")
        return Result.success()
    }
}