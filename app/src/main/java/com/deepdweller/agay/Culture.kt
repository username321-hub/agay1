package com.deepdweller.agay

class Culture(val name: String) {}
class Sequence(val previous:Culture, val next:Culture, val rate:Rate){}
enum class Rate{
    GOOD, MIDDLE, BAD
}