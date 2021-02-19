package com.deepdweller.agay

class Culture(val name: String){}

class PlantMaster {
    var isPlanted = false
    var isCanHarvest = false
    private val positiveMap = mutableMapOf<Culture, List<Culture>>(
        Data.cultures[0] to listOf(Data.cultures[1], Data.cultures[2]),
        Data.cultures[1] to listOf(Data.cultures[0], Data.cultures[2]),
        Data.cultures[2] to listOf(Data.cultures[1], Data.cultures[0]),
        Data.cultures[3] to listOf(Data.cultures[1], Data.cultures[0]),
        Data.cultures[4] to listOf(Data.cultures[1], Data.cultures[0])
    )

    fun howIsGoodChoice(prev : Culture, next : Culture) : Int{
        val isGoodNext = positiveMap[prev]?.contains(next) ?: false
        if (isGoodNext)
            return  1
        else
            return  0
    }


}

enum class Rate{
    RIGHT,
    WRONG
}