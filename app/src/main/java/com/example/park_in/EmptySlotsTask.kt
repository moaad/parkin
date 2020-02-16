package com.example.park_in

import android.os.AsyncTask
import org.json.JSONObject
import java.io.*
import java.net.URL

abstract class EmptySlotsTask : AsyncTask<String, Void, String>() {
    val logTag = "EmptySlotsTask"
    public override fun doInBackground(vararg args: String): String? {
        var parseString: String? = null
        var outData : String? = null
        try{
            val stream = BufferedInputStream(URL("https://umodyceboa.execute-api.us-east-2.amazonaws.com/default/lambda_handler").openStream())
            parseString = parseStream(stream)
        }

        catch (e : Exception)
        {
            System.out.println(e);
        }

        if (!parseString.isNullOrBlank()) {
            outData = JSONObject(parseString).getString("available_spaces")
        }


//        Log.d(logTag, "EmptySlotsTask: got $outData empty spaces")
        return outData
    }

    private fun parseStream(inData: InputStream): String {
        return inData.bufferedReader().use(BufferedReader::readText)
    }
}