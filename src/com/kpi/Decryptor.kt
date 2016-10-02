package com.kpi

import java.util.*

/**
 * Decrypt (c)

 * @author Vadim Ovcharenko
 * *         02.10.2016
 */
internal class Decryptor {

    companion object {
        val ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }

    fun decrypt(encrypted: String, initialKey: String, criterionEvaluator: CriterionEvaluator): String {
        var key = initialKey
        var criterion: Long = 0

        for (j in 0..99) {
            var randKey = key.shuffle()
            var randCriterion = criterionEvaluator.evaluate(decryptInternal(encrypted, randKey))

            var i = 0
            while (i < 1000) {
                val k = randKey.shuffleRandom()
                val result = criterionEvaluator.evaluate(decryptInternal(encrypted, k))
                if (result > randCriterion) {
                    randCriterion = result
                    randKey = k
                    i = 0
                }

                i++
            }

            if (randCriterion > criterion) {
                criterion = randCriterion
                key = randKey
            }
        }

        return decryptInternal(encrypted, key)
    }

    private fun decryptInternal(text: String, key: String): String {
        val keyValueMap = HashMap<Char, Char>()

        for (i in key.indices) {
            keyValueMap.put(key[i], ALPHABET[i])
        }

        val result = StringBuilder()
        for (ch in text.toCharArray()) {
            result.append(keyValueMap[ch])
        }

        return result.toString()
    }

    internal interface CriterionEvaluator {
        fun evaluate(text: String): Long
    }
}
