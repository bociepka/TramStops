package com.example.tramstops

class TramStop (id: Int, name:String, type:String){
    var id : Int = id

    var name : String = name

    var type : String = type

    override fun toString(): String {
        val res:String = id.toString()+" "+name
        return res
    }
}