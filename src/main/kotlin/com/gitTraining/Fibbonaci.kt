package com.gitTraining

fun computeFibbonaciNumber(position: Int?, recursion: Boolean = false): Int {
    var notNullPosition = position
    if (notNullPosition == null) {
        notNullPosition = 1
    }

    if (recursion) return recursiveFibbonachi(1, 1, notNullPosition - 2)

    if (notNullPosition == 0) return 0
    if (notNullPosition < 0) {
        return computeNegativeFibbonachi(notNullPosition)
    }

    var smallFibbonachiNumber = 1
    var largeFibbonachiNumber = 1

    if (notNullPosition <= 2) return 1

    var currentPosition = 2
    while (currentPosition < notNullPosition) {
        val nextFibbonachiNumber = smallFibbonachiNumber + largeFibbonachiNumber
        smallFibbonachiNumber = largeFibbonachiNumber
        largeFibbonachiNumber = nextFibbonachiNumber
        currentPosition ++
    }
    return largeFibbonachiNumber
}

fun computeNegativeFibbonachi(position:Int): Int {
    if (position >= 0) throw Exception("potition must be smaller than zero!")
    val resultIsNegative = position % 2 == 0
    val absoluteResult = computeFibbonaciNumber(-position)
    return if (resultIsNegative) (absoluteResult * -1) else absoluteResult
}

fun recursiveFibbonachi(previous: Int, current: Int, stepsLeft: Int): Int {
    if (stepsLeft < 0) return 1
    return when (stepsLeft) {
        0 -> current
        else -> recursiveFibbonachi(current, previous + current, stepsLeft - 1)
    }
}
fun computeFibbonachiArray(start: Int, end: Int, efficient: Boolean = false): List<Int> {
    if (!efficient) return (start..end).map { computeFibbonaciNumber(it) }
    if (start > end) return listOf()
    if (start == end) return listOf(computeFibbonaciNumber(start))
    val output = mutableListOf(computeFibbonaciNumber(start), computeFibbonaciNumber(start + 1))
    (2..(end-start)).forEach { output.add(output[it-2] + output[it-1]) }
    return output
}