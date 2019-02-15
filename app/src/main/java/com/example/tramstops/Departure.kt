package com.example.tramstops

class Departure (
    var display_time: String,
    var line_number: String,
    var direction:String,
    var category:String,
    var actual_time:String,
    var delay:String
){



    override fun toString(): String {
        return "Odjazd: " + display_time + " Linia: " + line_number + "\n " + direction +"  "+delay
    }
}