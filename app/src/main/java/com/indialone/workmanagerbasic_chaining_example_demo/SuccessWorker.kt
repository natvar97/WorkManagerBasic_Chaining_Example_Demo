package com.indialone.workmanagerbasic_chaining_example_demo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SuccessWorker(context: Context , params : WorkerParameters) : Worker(context , params) {
    override fun doWork(): Result {
        makeStatusNotification("Image Successfully edited..." , applicationContext)
        Thread.sleep(2000)
        Log.e("edited","image is successfully edited...")
        return Result.success()
    }
}