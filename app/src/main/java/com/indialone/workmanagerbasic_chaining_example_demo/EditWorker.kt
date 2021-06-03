package com.indialone.workmanagerbasic_chaining_example_demo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class EditWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        makeStatusNotification("Editing started...", applicationContext)
        Thread.sleep(2000)
        Log.e("editing", "editing started ...")
        return try {
            Log.e("editing", "editing running ...")
            Result.success()
        } catch (e: Exception) {
            Log.e("exception", "error in editing")
            Result.failure()
        }
    }
}