package com.hn.insave.utils

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.hn.insave.model.InstaUserModel
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class VolleyRequestUtil private constructor(context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)
    fun get(url: String, instaUserModel: InstaUserModel, listener: RequestCompleteListener) {
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(Method.GET, url, null, Response.Listener { response: JSONObject? ->
            try {
                listener.onResponse(true, response)
            } catch (e: Exception) {
                e.printStackTrace()
                try {
                    listener.onResponse(false, null)
                } catch (ex: JSONException) {
                    ex.printStackTrace()
                }
            }
        }, Response.ErrorListener { error: VolleyError ->
            error.printStackTrace()
            try {
                listener.onResponse(false, null)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Instagram 9.5.2 (iPhone7,2; iPhone OS 9_3_3; en_US; en-US; scale=2.00; 750x1334) AppleWebKit/420+"
                headers["Cookie"] = "ds_user_id=" + instaUserModel.ds_user_id + ";sessionid=" + instaUserModel.sessionid
                return headers
            }
        }
        queue.add(jsonObjectRequest)
    }

    interface RequestCompleteListener {
        @Throws(JSONException::class)
        fun onResponse(isSuccessFull: Boolean, response: JSONObject?)
    }

    companion object {
        fun init(context: Context): VolleyRequestUtil {
            return VolleyRequestUtil(context)
        }
    }

}