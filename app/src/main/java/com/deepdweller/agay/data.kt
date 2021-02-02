package com.deepdweller.agay

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
        Culture("Пшеница"),
        Culture("Овёс"),
        Culture("Ячмень")
        )
    val sequences:List<Sequence> = listOf(
        Sequence(cultures.first { culture -> culture.name.equals("Рожь") }, cultures.first { culture -> culture.name.equals("Рожь")}, Rate.MIDDLE)
    )
}