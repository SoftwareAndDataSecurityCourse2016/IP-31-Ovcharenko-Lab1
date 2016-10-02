package com.kpi

import java.util.*

fun String.shuffleRandom(): String {
    val random = Random()
    val result = StringBuilder()
    val a = random.nextInt(this.length)
    val b = random.nextInt(this.length)
    for (i in this.indices) {
        val ch = when(i) {
            a -> this[b]
            b -> this[a]
            else -> this[i]
        }
        result.append(ch)
    }
    return result.toString()
}

fun String.shuffle() : String {
    val characters = Arrays.asList(this.toCharArray())
    Collections.shuffle(characters)
    val sb = StringBuilder()
    characters.forEach { sb.append(it) }
    return sb.toString()
}
