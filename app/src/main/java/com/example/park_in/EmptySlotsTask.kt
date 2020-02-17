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
    public override fun doInBackground(vararg args: String): String? {
        var parseString: String? = null
        var outData : String? = null
        try{
            Log.d(logTag, "fetching data")
            val stream = BufferedInputStream(URL("https://umodyceboa.execute-api.us-east-2.amazonaws.com/default/lambda_handler").openStream())
            parseString = stream.bufferedReader().use(BufferedReader::readText)
        }
        catch (e : Exception)
        {
            System.out.println(e);
            return null
        }
        outData = JSONObject(parseString).getString("available_spaces")

        Log.d(logTag, "got $outData empty spaces")
        return outData
    }
}