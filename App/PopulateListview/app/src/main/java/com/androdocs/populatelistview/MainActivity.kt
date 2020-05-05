package com.androdocs.populatelistview

import android.os.AsyncTask
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {

    var dataList = ArrayList<HashMap<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchJsonData().execute()
    }


    inner class fetchJsonData() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: String?): String? {
            //String url = "https://www.androdocs.com/files/uploads/original/sample-json-data-1567767983.txt";
            return URL("https://smartfridge-app.herokuapp.com/shoplist/getItems").readText(
                    Charsets.UTF_8
                )
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            findViewById<ProgressBar>(R.id.loader).visibility = View.GONE

            val jsonObj = JSONObject(result)




            // Debug JSON
            val TAG = MainActivity::class.java.simpleName


            val keys: Iterator<String> = jsonObj.keys()

            while (keys.hasNext()) {
                val key = keys.next()
                if (jsonObj.get(key) is JSONObject) {
                    val item = (jsonObj.get(key) as JSONObject).getJSONObject("shopitem")
                    val map = HashMap<String, String>()
                    map["name"] = item.get("name").toString()
                    map["age"] = item.get("notes").toString()
                    map["city"] = item.get("category").toString()
                    map["image"] = item.get("url_img").toString()
                    val url_back = (jsonObj.get(key) as JSONObject).get("delete_callback")
                    map["callback"] = url_back.toString()
                    dataList.add(map)
                }
            }

            findViewById<ListView>(R.id.listView).adapter = CustomAdapter(this@MainActivity, dataList)

        }


    }
}
