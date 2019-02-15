package com.example.tramstops

class TramStop (
    var id: Int,
    var name: String,
    var type: String){

    override fun toString(): String {
        val res:String = id.toString()+" "+name
        return res
    }
}