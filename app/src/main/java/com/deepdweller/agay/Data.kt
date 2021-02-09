package com.deepdweller.agay

object Data {
    val plants = arrayOf("Рожь", "Овёс", "Пшеница")

    var counter = 0

    var score:MutableList<Rate> = mutableListOf()



    var rline:FloatArray = floatArrayOf(0f, 0f, 0f, 0f)
    var gline:FloatArray = floatArrayOf(0f, 0.4f, 0f, 0f)
    var bline:FloatArray = floatArrayOf(0f, 0f, 0f, 0f)
    var lline:FloatArray = floatArrayOf(0f, 0f, 0f, 0.4f)


    val cultures:List<Culture> = listOf(
        Culture("Рожь"),
        Culture("Овёс"),
        Culture("Пшеница")
    )

}



/*object CheckGood {
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

}*/

object History{
    var plantHistory:MutableList<Culture> = mutableListOf()
    var plantHistoryString:MutableList<String> = mutableListOf()
}