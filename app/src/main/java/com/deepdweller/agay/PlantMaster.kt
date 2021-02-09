package com.deepdweller.agay

class PlantMaster {
    private val positiveMap = mutableMapOf<Culture, List<Culture>>(
        Data.cultures[0] to listOf(Data.cultures[1], Data.cultures[2]),
        Data.cultures[1] to listOf(Data.cultures[0], Data.cultures[2]),
        Data.cultures[2] to listOf(Data.cultures[1], Data.cultures[0])
    )

    fun howIsGoodChoice(prev : Culture, next : Culture) : Rate{
        val isGoodNext = positiveMap[prev]?.contains(next) ?: false
        if (isGoodNext)
            return  Rate.GOOD
        else
            return  Rate.BAD
    }
}

enum class Rate{
    GOOD, MIDDLE, BAD
}