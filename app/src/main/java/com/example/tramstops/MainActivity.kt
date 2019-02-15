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


    private fun createStops(): MutableList<TramStop> {
        val g = Gson()
        val stopsList = mutableListOf<TramStop>()
        val inputStream = BufferedReader(
            InputStreamReader(assets.open("TramStopsList"))
        )
        inputStream.use{
            var inputLine = it.readLine()
            while (inputLine != null) {
                val currentStop: TramStop = g.fromJson(inputLine, TramStop::class.java)
                stopsList.add(currentStop)
                inputLine = it.readLine()
            }
        }
        return stopsList
    }

    fun chosenStop(id :String){
        val chosenIntent = Intent(this, SecondaryActivity::class.java)
        chosenIntent.putExtra(SecondaryActivity.STOP_ID, id)
        startActivity(chosenIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

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
