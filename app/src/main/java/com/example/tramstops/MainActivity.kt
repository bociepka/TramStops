package com.example.tramstops

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private fun sendGet() {
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

    }







    var lista  = Array<TramStop>(150, {i -> TramStop(i,"Stop","")})

    override fun onCreate(savedInstanceState: Bundle?) {
//        var count = 0
//        for(i in lista){
//            Log.d("TAG", i)
//            lista[count]="Przystanek"+(count+1)
//            count++
//        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        val listView: ListView = findViewById(R.id.ListView)
        listView.adapter = adapter
        sendGet()
    }


}
