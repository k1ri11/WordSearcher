package org.example

import org.example.impl.FileManagerImpl
import java.io.IOException

fun main() {

    println("Введите путь к текстовому файлу ")
    val path = readlnOrNull()?.trim()

    println("Введите слово для поиска")
    val word = readlnOrNull()?.trim()

    if (isUserInputInvalid(path, word)) {
        println("Некорректно заданы входные данные ")
        return
    }

    try {
        val (totalWordsCount, wordCount) = FileManagerImpl().countWordsInFile(path!!, word!!)
        println("Общее количество слов в файле = $totalWordsCount \nКоличество повторений заданного слова = $wordCount")
    } catch (e: IOException) {
        println(e.message)
    }

}

internal fun isUserInputInvalid(path: String?, word: String?): Boolean =
    path.isNullOrBlank() || word.isNullOrBlank()

