package com.example.tramstops

class Departure (
    var display_time: String,
    var line_number: String,
    var direction:String,
    var category:String,
    var actual_time:String
){



    override fun toString(): String {
        val res:String = display_time+" "+line_number+" "+direction
        return res
    }
}