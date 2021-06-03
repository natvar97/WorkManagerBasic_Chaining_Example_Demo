package com.indialone.workmanagerbasic_chaining_example_demo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*

class MainActivity : AppCompatActivity() {

    private var workManager = WorkManager.getInstance(this)

    companion object {
        const val UNIQUE_WORK_NAME = "Image Editing Started"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cancelButton = findViewById<Button>(R.id.buttonCancel)

        findViewById<Button>(R.id.buttonChain).setOnClickListener {
            chainingTheWorkers()
            cancelButton.visibility = View.VISIBLE
        }

        cancelButton.setOnClickListener {
            workManager.cancelUniqueWork(UNIQUE_WORK_NAME)
        }

    }

    private fun chainingTheWorkers() {

        var continuation = workManager.beginUniqueWork(
            UNIQUE_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.from(EditWorker::class.java)
        )

        val constrains = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        val edit = OneTimeWorkRequestBuilder<CropWorker>()
            .setConstraints(constrains)
            .build()

        continuation = continuation.then(edit)

        val resize = OneTimeWorkRequestBuilder<ResizeWorker>()
            .setConstraints(constrains)
            .build()

        continuation = continuation.then(resize)

        val success = OneTimeWorkRequestBuilder<SuccessWorker>()
            .setConstraints(constrains)
            .build()

        continuation = continuation.then(success)

        continuation.enqueue()

    }
}