package com.example.park_in

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val logTag = "MainActivity"

    inner class updateUi : EmptySlotsTask() {
        override fun onPostExecute(result: String) {
            Log.d(logTag, "onPostExecute(updateUi): update empty slots")

            val stringToUpdate = getString(R.string.emptySlots) + result
            counterTextBox.setText(stringToUpdate)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateEmptySlots()

        checkForpark.setOnClickListener {
            Log.d(logTag, "+onClick")
            updateEmptySlots()
        }

    }

    internal fun updateEmptySlots() {
        updateUi().execute()
    }


}
