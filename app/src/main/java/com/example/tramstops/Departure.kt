package com.example.tramstops

class Departure (
    var display_time: String,
    var line_number: String,
    var direction:String,
    var category:String,
    var actual_time:String
){



    override fun toString(): String {
        val res:String = "Odjazd: "+display_time+" Linia: "+line_number+"\n "+direction
        return res
    }
}