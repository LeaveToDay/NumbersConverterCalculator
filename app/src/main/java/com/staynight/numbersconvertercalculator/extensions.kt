package com.staynight.numbersconvertercalculator

import kotlin.math.floor
import kotlin.math.pow

private val hexMap = mapOf(
    Pair(10, "A"),
    Pair(11, "B"),
    Pair(12, "C"),
    Pair(13, "D"),
    Pair(14, "E"),
    Pair(15, "F")
)

fun Double.decToBin(): String {
    val whole = floor(this).toLong()
    var result = ""
    var doublePart = this - whole

    var intPart = whole
    while (intPart != 0L) {
        result = "${intPart % 2}$result"
        intPart /= 2
    }

    if (doublePart != 0.0) {
        result += "."

        repeat(10) {
            val r = doublePart * 2.0
            if (r >= 1.0) {
                result += "1"
                doublePart = r - 1
            } else {
                result += "0"
                doublePart = r
            }
        }
    }
    return result
}

fun String.binToDec(): String {
    var sum = 0.0
    if (this.contains(".")) {
        val divideToIntAndDouble = this.split(".")
        divideToIntAndDouble[0].forEachIndexed { index, char ->
            sum += char.toString()
                .toInt() * 2.0.pow((divideToIntAndDouble[0].length - 1 - index).toDouble())
        }

        divideToIntAndDouble[1].forEachIndexed { index, char ->
            sum += char.toString().toInt() * 2.0.pow((0 - 1 - index).toDouble())
        }
    } else {
        this.forEachIndexed { index, char ->
            sum += char.toString().toInt() * 2.0.pow((this.length - 1 - index).toDouble())
        }
    }
    return sum.toString()
}

fun Double.decToOctal(): String {
    val whole = floor(this).toLong()
    var result = ""
    var doublePart = this - whole

    var intPart = whole
    while (intPart != 0L) {
        result = "${intPart % 8}$result"
        intPart /= 8
    }

    if (doublePart != 0.0) {
        result += "."

        repeat(10) {
            val r = doublePart * 8.0
            if (r >= 1.0) {
                result += r.toInt()
                doublePart = r - r.toInt()
            } else {
                result += "0"
                doublePart = r
            }
        }
    }
    return result
}

fun String.octalToDec(): String {
    var sum = 0.0
    if (this.contains(".")) {
        val divideToIntAndDouble = this.split(".")
        divideToIntAndDouble[0].forEachIndexed { index, char ->
            sum += char.toString()
                .toInt() * 8.0.pow((divideToIntAndDouble[0].length - 1 - index).toDouble())
        }

        divideToIntAndDouble[1].forEachIndexed { index, char ->
            sum += char.toString().toInt() * 8.0.pow((0 - 1 - index).toDouble())
        }
    } else {
        this.forEachIndexed { index, char ->
            sum += char.toString().toInt() * 8.0.pow((this.length - 1 - index).toDouble())
        }
    }
    return sum.toString()
}

fun Double.decToHex(): String {
    val whole = floor(this).toLong()
    var result = ""
    var doublePart = this - whole

    var intPart = whole
    while (intPart != 0L) {
        val number = if (intPart % 16 > 9)
            hexMap[(intPart % 16).toInt()]
        else
            intPart % 16

        result = "$number$result"
        intPart /= 16
    }

    if (doublePart != 0.0) {
        result += "."

        repeat(10) {
            val r = doublePart * 16.0
            if (r >= 1.0) {
                val number = if (r.toInt() > 9)
                    hexMap[(r.toInt())]
                else
                    r.toInt()

                result += number
                doublePart = r - r.toInt()
            } else {
                result += "0"
                doublePart = r
            }
        }
    }
    return result
}

fun String.hexToDec(): String {
    var sum = 0.0
    if (this.contains(".")) {
        val divideToIntAndDouble = this.split(".")
        divideToIntAndDouble[0].forEachIndexed { index, char ->
            val number = if (char.toString().toIntOrNull() != null)
                char.toString().toInt()
            else
                getKey(hexMap, char.toString())!!

            sum += number * 16.0.pow((divideToIntAndDouble[0].length - 1 - index).toDouble())
        }

        divideToIntAndDouble[1].forEachIndexed { index, char ->
            val number = if (char.toString().toIntOrNull() != null)
                char.toString().toInt()
            else
                getKey(hexMap, char.toString())!!

            sum += number * 16.0.pow((0 - 1 - index).toDouble())
        }
    } else {
        this.forEachIndexed { index, char ->
            val number = if (char.toString().toIntOrNull() != null)
                char.toString().toInt()
            else
                getKey(hexMap, char.toString())!!

            sum += number * 16.0.pow((this.length - 1 - index).toDouble())
        }
    }
    return sum.toString()
}

fun <K, V> getKey(map: Map<K, V>, value: V): K? {
    for (key in map.keys) {
        if (value == map[key]) {
            return key
        }
    }
    return null
}

fun addBinary(binary1: String, binary2: String): String {
    val first = binary1.binToDec()
    val second = binary2.binToDec()

    return (first.toDouble() + second.toDouble()).decToBin()
}

fun subtractBinary(binary1: String, binary2: String): String {
    val first = binary1.binToDec()
    val second = binary2.binToDec()

    return (first.toDouble() - second.toDouble()).decToBin()
}

fun multiplyBinary(binary1: String, binary2: String): String {
    val first = binary1.binToDec()
    val second = binary2.binToDec()

    return (first.toDouble() * second.toDouble()).decToBin()
}

fun divideBinary(binary1: String, binary2: String): String {
    val first = binary1.binToDec()
    val second = binary2.binToDec()

    return (first.toDouble() / second.toDouble()).decToBin()
}

fun addOctal(octal1: String, octal2: String): String {
    val first = octal1.octalToDec()
    val second = octal2.octalToDec()

    return (first.toDouble() + second.toDouble()).decToOctal()
}

fun subtractOctal(octal1: String, octal2: String): String {
    val first = octal1.octalToDec()
    val second = octal2.octalToDec()

    return (first.toDouble() - second.toDouble()).decToOctal()
}

fun multiplyOctal(octal1: String, octal2: String): String {
    val first = octal1.octalToDec()
    val second = octal2.octalToDec()

    return (first.toDouble() * second.toDouble()).decToOctal()
}

fun divideOctal(octal1: String, octal2: String): String {
    val first = octal1.octalToDec()
    val second = octal2.octalToDec()

    return (first.toDouble() / second.toDouble()).decToOctal()
}

fun addHex(hex1: String, hex2: String): String {
    val first = hex1.octalToDec()
    val second = hex2.octalToDec()

    return (first.toDouble() + second.toDouble()).decToHex()
}

fun subtractHex(hex1: String, hex2: String): String {
    val first = hex1.octalToDec()
    val second = hex2.octalToDec()

    return (first.toDouble() - second.toDouble()).decToHex()
}

fun multiplyHex(hex1: String, hex2: String): String {
    val first = hex1.octalToDec()
    val second = hex2.octalToDec()

    return (first.toDouble() * second.toDouble()).decToHex()
}

fun divideHex(hex1: String, hex2: String): String {
    val first = hex1.octalToDec()
    val second = hex2.octalToDec()

    return (first.toDouble() / second.toDouble()).decToHex()
}
