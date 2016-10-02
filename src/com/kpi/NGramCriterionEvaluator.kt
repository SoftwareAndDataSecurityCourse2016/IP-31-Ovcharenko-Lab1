package com.kpi

import java.io.BufferedReader
import java.io.FileReader
import java.util.*

/**
 * Decrypt (c)

 * @author Vadim Ovcharenko
 * *         02.10.2016
 */
class NGramCriterionEvaluator(private val N: Int, file: String) : Decryptor.CriterionEvaluator {

    private val weights = HashMap<String, Long>()

    init {
        val bufferedReader = BufferedReader(FileReader(file))
        bufferedReader.forEachLine {
            val (trigram, weight) = it.split(" ")
            weights.put(trigram, weight.toLong())
        }
    }

    override fun evaluate(text: String): Long {
        var criterion: Long = 0
        for (i in 0..text.length - N - 1) {
            val trigram = text.substring(i, i + N)
            if (weights.containsKey(trigram)) {
                criterion += weights[trigram]!!
            }
        }
        return criterion
    }
}
