package com.example.park_in

import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.*
import java.net.URL
import kotlin.math.roundToInt

/**
 * function onPostExecute(result: String?) must be implemented in the new class
 *
 * In order to get data, run EmptySlotsTask().execute()
 */
abstract class EmptySlotsTask : AsyncTask<String, Void, String>() {
    open val logTag = "EmptySlotsTask"
    private val apiUrl = "https://umodyceboa.execute-api.us-east-2.amazonaws.com/default/lambda_handler"

    public override fun doInBackground(vararg args: String): String? {
        val parseString: String?
        val emptySpots: String?
        try {
            Log.d(logTag, "Fetching data...")
            val stream = BufferedInputStream(URL(apiUrl).openStream())
            parseString = stream.bufferedReader().use(BufferedReader::readText)
            emptySpots = JSONObject(parseString).getString("available_spaces")
            Log.d(logTag, "Got data from the API")
            Log.d(logTag, "There are $emptySpots empty spots")
        } catch (e: Exception) {
            println(e);
            return null
        }

        return emptySpots
    }
}