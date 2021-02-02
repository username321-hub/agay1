package com.deepdweller.agay

import com.deepdweller.agay.data.cultures

object data {
    var r = 127
    var g = 127
    var b = 127
    var rline:FloatArray = floatArrayOf(0f, 0f, 0f, 0f)
    var gline:FloatArray = floatArrayOf(0f, 0.4f, 0f, 0f)
    var bline:FloatArray = floatArrayOf(0f, 0f, 0f, 0f)
    var lline:FloatArray = floatArrayOf(0f, 0f, 0f, 0.4f)
    val cultures:List<Culture> = listOf(
        Culture("Рожь"),
        Culture("Овёс"),
        Culture("Пшеница")
        )
    var sequences:List<Sequence> = listOf(
        Sequence(cultures.first { culture -> culture.name.equals("Пшеница") }, cultures.first { culture -> culture.name.equals("Овёс")}, Rate.BAD),
        Sequence(cultures.first { culture -> culture.name.equals("Пшеница") }, cultures.first { culture -> culture.name.equals("Пшеница")}, Rate.MIDDLE),
        Sequence(cultures.first { culture -> culture.name.equals("Пшеница") }, cultures.first { culture -> culture.name.equals("Рожь")}, Rate.GOOD)
    )

    class Sequence(val previous:Culture, val next:Culture, val rate:Rate){}



}

enum class Rate{
    GOOD, MIDDLE, BAD
}

object CheckGood {
    private val positiveMap = mutableMapOf<Culture, List<Culture>>(
        data.cultures[0] to listOf(cultures[1], cultures[2]),
        data.cultures[1] to listOf(cultures[0], cultures[2]),
        data.cultures[2] to listOf(cultures[1], cultures[0])
    )

    fun c(prev : Culture, next : Culture) : Rate{
        val isGoodNext = positiveMap[prev]?.contains(next) ?: false
        if (isGoodNext)
            return  Rate.GOOD
        else
            return  Rate.BAD
    }

}

object History{
    var plantHistory:MutableList<Culture> = mutableListOf()
}