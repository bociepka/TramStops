package com.example.tramstops

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {




    /*private fun sendGet() {
        val thread = Thread(Runnable {
            try {
                val url = "https://krakowpodreka.pl/pl/stops/positions/stops"
                val obj = URL(url)

                with(obj.openConnection() as HttpURLConnection) {
                    // optional default is GET
                    requestMethod = "GET"


                    println("\nSending 'GET' request to URL : $url")
                    println("Response Code : $responseCode")

                    BufferedReader(InputStreamReader(inputStream)).use {
                        val response = StringBuffer()

                        var inputLine = it.readLine()
                        while (inputLine != null) {
                            response.append(inputLine)
                            println(inputLine.toString())
                            inputLine = it.readLine()
                        }
                        println(response.toString())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        thread.start()

    }*/

    private fun createStops(): MutableList<TramStop> {
        val g = Gson()
        val stopsList = mutableListOf<TramStop>()
        val inputStream = BufferedReader(
            InputStreamReader(assets.open("TramStopsList"))
        )
//        val inputStream: InputStream = File("TramStops.txt").inputStream()
//        inputStream.bufferedReader().useLines { lines -> lines.forEach { stopsList.add(/****/)} }
        inputStream.use{
            var inputLine = it.readLine()
            while (inputLine != null) {
                println(inputLine)
                var currentStop: TramStop = g.fromJson(inputLine, TramStop::class.java)
                println(currentStop)
                stopsList.add(currentStop)
                inputLine = it.readLine()
            }
        }
        return stopsList
    }




//    var lista  = Array<TramStop>(150, {i -> TramStop(i,"Stop","")})

    fun chosenStop(id :String){
        val chosenIntent = Intent(this, SecondaryActivity::class.java)
        chosenIntent.putExtra(SecondaryActivity.STOP_ID, id)
        startActivity(chosenIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        var count = 0
//        for(i in lista){
//            Log.d("TAG", i)
//            lista[count]="Przystanek"+(count+1)
//            count++
//        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val stopsList = createStops()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stopsList)
        val listView: ListView = findViewById(R.id.ListView)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val newId:String = (id+1).toString()
            chosenStop(newId)
        }



    }


}
