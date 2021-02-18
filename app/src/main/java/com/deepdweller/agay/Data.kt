package com.deepdweller.agay

object Data {
    val plants = arrayOf("Рожь", "Овёс", "Пшеница", "Гречиха", "Просо")

    var counter = 0

    lateinit var rline: FloatArray
    lateinit var gline: FloatArray
    lateinit var bline: FloatArray
    lateinit var lline: FloatArray

    init {
        initFilter()
    }

    fun initFilter() {
        rline = floatArrayOf(0f, 0f, 0f, 0f)
        gline = floatArrayOf(0f, 0.4f, 0f, 0f)
        bline = floatArrayOf(0f, 0f, 0f, 0f)
        lline = floatArrayOf(0f, 0f, 0f, 0.4f)
    }

    val cultures: List<Culture> = listOf(
        Culture("Рожь"),
        Culture("Овёс"),
        Culture("Пшеница"),
        Culture("Гречиха"),
        Culture("Просо")
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

object History {
    var plantHistory: MutableList<Culture> = mutableListOf()
}
object Eventik{
    val solutions:Array<Instrument> = arrayOf(
        Instrument("Плуг"),
        Instrument("Культиватор"),
        Instrument("Катки")
        )
    var instruments:MutableList<Instrument> = mutableListOf(
        Instrument("Плуг"),
        Instrument("Борона"),
        Instrument("Лущильник"),
        Instrument("Культиватор"),
        Instrument("Катки")
    )
    var instrumentsString:Array<String> = arrayOf(
        "Плуг",
        "Борона",
        "Лущильник",
        "Культиватор",
        "Катки"
    )
    val event = Event("Сорняки!", solutions)
}