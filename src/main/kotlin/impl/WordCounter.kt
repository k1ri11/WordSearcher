package org.example.impl

import java.io.File

/**
 * Подсчитывает общее количество слов в файле и количество вхождений заданного слова.
 *
 * @receiver Файл, в котором производится подсчет.
 * @param word Слово, количество вхождений которого необходимо подсчитать.
 * @return Пара значений: первое — общее количество слов в файле, второе — количество вхождений указанного слова.
 */
internal fun File.countWords(word: String): Pair<Long, Long> {
    var totalWordsCount = 0L
    var wordsCount = 0L
    this.bufferedReader(Charsets.UTF_8).forEachLine { line ->
        val words = line.split("\\s+".toRegex()).filter { it.isNotBlank() }
        totalWordsCount += words.size
        wordsCount += words.count { it.equals(word, ignoreCase = true) }
    }
    return (totalWordsCount to wordsCount)
}