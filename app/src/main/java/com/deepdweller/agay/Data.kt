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
        gline = floatArrayOf(0f, 0.8f, 0f, 0f)
        bline = floatArrayOf(0f, 0f, 0f, 0f)
        lline = floatArrayOf(0f, 0f, 0f, 0.9f)
    }

    val cultures: List<Culture> = listOf(
        Culture("Рожь", "Озимые", "Зерновые", "Многолетние"),
        Culture("Овёс", "Озимые", "Зерновые", "Многолетние"),
        Culture("Пшеница", "Озимые", "Зерновые", "Многолетние"),
        Culture("Гречиха", "Озимые", "Зерновые", "Многолетние"),
        Culture("Просо", "Озимые", "Зерновые", "Многолетние"),
        Culture("Подсолнечник", "Озимые", "Зерновые", "Многолетние"),
        Culture("Горох", "Озимые", "Зернобобовые", "Многолетние"),
        Culture("Картофель", "Озимые", "КоромовыеКорнеплоды", "Многолетние")
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
    val solutions:Array<Harvesters> = arrayOf(
        Harvesters("Комбайн")
        )
    var instruments:MutableList<Harvesters> = mutableListOf(
        Harvesters("Плуг"),
        Harvesters("Борона"),
        Harvesters("Лущильник"),
        Harvesters("Комбайн"),
        Harvesters("Культиватор"),
        Harvesters("Катки"),
    )
    var instrumentsString:Array<String> = arrayOf(
        "Плуг",
        "Борона",
        "Лущильник",
        "Комбайн",
        "что-то против соринков",
        "Катки"
    )
    val event = Event("Сорняки!", solutions)
}