package com.deepdweller.agay

class Instrument(val name:String){}
var instruments:MutableList<Instrument> = mutableListOf(
    Instrument("Плуг"),
    Instrument("Борона"),
    Instrument("Лущильник"),
    Instrument("Комбайн"),
    Instrument("Культиватор"),
    Instrument("Катки"),

    )
//Culture("Рожь"),
//Culture("Овёс"),
//Culture("Пшеница"),
//Culture("Гречиха"),
//Culture("Просо"),
//Culture("Подсолнечник"),
//Culture("Картофель")
private val map_instruments_positive = mutableMapOf<Culture, MutableList<Instrument>>(
    Data.cultures[0] to mutableListOf(instruments[2], instruments[3], instruments[3]),
    Data.cultures[1] to mutableListOf(instruments[2], instruments[3], instruments[3]),
    Data.cultures[2] to mutableListOf(instruments[2], instruments[3], instruments[3]),
    Data.cultures[3] to mutableListOf(instruments[2], instruments[3]),
    Data.cultures[4] to mutableListOf(instruments[2], instruments[3]),
    Data.cultures[5] to mutableListOf(instruments[2], instruments[3]),
    Data.cultures[6] to mutableListOf(instruments[2], instruments[3]),
)





