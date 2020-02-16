package com.example.park_in

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.*
import java.net.URL

abstract class EmptySlotsTask : AsyncTask<String, Void, String>() {
    val logTag = "EmptySlotsTask"
    public override fun doInBackground(vararg args: String): String? {
        val url = URL("https://umodyceboa.execute-api.us-east-2.amazonaws.com/default/lambda_handler")
        val inData = readStream(BufferedInputStream(url.openStream()))
        val outData = JSONObject(inData).getString("available_spaces")

        Log.d(logTag, "EmptySlotsTask: got $outData empty spaces")
        return outData
    }

    private fun readStream(inData: InputStream): String {
        return inData.bufferedReader().use(BufferedReader::readText)
    }
}