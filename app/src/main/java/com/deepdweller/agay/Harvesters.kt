package com.deepdweller.agay

class Harvesters(val name:String){}
class Cultivator(val name: String){}
class Chemicals(val name:String){}

class InstrumentMaster(){
    var instrumentCanBeUsed = true
    private val map_instruments_positive = mutableMapOf<Culture, MutableList<Harvesters>>(
        Data.cultures[0] to mutableListOf(harvesters[2], harvesters[3]),
        Data.cultures[1] to mutableListOf(harvesters[2], harvesters[3]),
        Data.cultures[2] to mutableListOf(harvesters[2], harvesters[3]),
        Data.cultures[3] to mutableListOf(harvesters[2], harvesters[3]),
        Data.cultures[4] to mutableListOf(harvesters[2], harvesters[3]),
        Data.cultures[5] to mutableListOf(harvesters[2], harvesters[3]),
        Data.cultures[6] to mutableListOf(harvesters[2], harvesters[3]),
    )
    fun InstrumentUse(instrument: Harvesters, culture: Culture):String{
        val Check = map_instruments_positive[culture]?.contains(instrument) ?: false
        return if (Check)
            "RIGHT"
        else
            "WRONG"
    }
}
var harvesters:MutableList<Harvesters> = mutableListOf(
    Harvesters("Плуг"),
    Harvesters("Борона"),
    Harvesters("Лущильник"),
    Harvesters("Комбайн"),
    Harvesters("что-то против соринков"),
    Harvesters("Катки"),
)
//Culture("Рожь"),
//Culture("Овёс"),
//Culture("Пшеница"),
//Culture("Гречиха"),
//Culture("Просо"),
//Culture("Подсолнечник"),
//Culture("Картофель")






