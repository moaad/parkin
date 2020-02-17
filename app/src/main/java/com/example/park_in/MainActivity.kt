package com.example.park_in

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val logTag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateEmptySlots()

        checkForpark.setOnClickListener {
            Log.d(logTag, "+onClick")
            updateEmptySlots()
        }
    }

    private fun updateEmptySlots() {
        updateUi().execute()
    }

    inner class updateUi : EmptySlotsTask() {
        override var logTag = "updateUi"

        override fun onPostExecute(result: String?) {
            Log.d(logTag, "update empty slots")

            val stringToUpdate = getString(R.string.emptySlots) + result?:""
            counterTextBox.setText(stringToUpdate)
        }
    }
}
