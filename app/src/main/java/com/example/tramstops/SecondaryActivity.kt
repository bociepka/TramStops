package com.example.tramstops

import android.drm.DrmStore.Playback.STOP
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SecondaryActivity : AppCompatActivity() {

    companion object {
        var STOP_ID = "0"
    }
    private fun sendGet():String{
        val id = intent.getStringExtra(STOP_ID)
        var result= ""
        val thread = Thread(Runnable {
            try {
                val url = "https://krakowpodreka.pl/pl/stops/schedule/stop/$id"
                val obj = URL(url)

                with(obj.openConnection() as HttpURLConnection) {
                    // optional default is GET
                    requestMethod = "GET"


                    println("\nSending 'GET' request to URL : $url")
                    println("Response Code : $responseCode")
//                    result = inputStream.toString()
                    BufferedReader(InputStreamReader(inputStream)).use {
                        val response = StringBuffer()

                        var inputLine = it.read().toChar()
                            while (inputLine != ']') {
                            response.append(inputLine)
//                            println(inputLine.toString())
                            inputLine = it.read().toChar()
                        }
                        result = response.toString()
                        STOP_ID=result
//                        println(result)
                        result.substring(12)
//                        println(result)


                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            finally{

            }
        })
        thread.start()
        thread.join()
        return result
    }


    private fun createDepartures(jsonArray:List<String>): MutableList<Departure> {
        val g = Gson()
        val departuresList = mutableListOf<Departure>()
            for (i in jsonArray) {
                val currentDeparture: Departure = g.fromJson(i, Departure::class.java)
//                println(currentDeparture)
                departuresList.add(currentDeparture)
            }
        return departuresList
            }

    /*private fun createDepartMap(list:MutableList<Departure>):MutableMap<String,String>{
        val departMap : MutableMap<String,String>
        for (i in list){
            departMap.put(i.display_time+" Linia: "+i.line_number,i.direction)
        }
        return departMap
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
        var departsString:String = sendGet()
//        println(stops)
        departsString = departsString.substring(12)
//        println(departs)
        departsString = departsString.replace("}, {","}#{")
        var departsStringList = departsString.split("#")
        for (i in departsStringList){println(i)}

        var departureList = createDepartures(departsStringList)
//        var departureMap = createDepartMap(departureList)
//        println(departureList)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, departureList)
        val listView2: ListView = findViewById(R.id.ListView2)
        listView2.adapter = adapter2

    }
}
