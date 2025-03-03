package org.example.impl

import org.example.api.FileManager
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Класс для подсчета слов в файле.
 */
internal class FileManagerImpl : FileManager {

    /**
     * Подсчитывает общее количество слов в файле и количество вхождений заданного слова.
     *
     * @param pathName Путь к файлу.
     * @param word Слово, которое нужно найти в файле.
     * @return Пара (totalWordsCount, wordCount), где:
     *         - `totalWordsCount` — общее количество слов в файле.
     *         - `wordCount` — количество вхождений заданного слова.
     * @throws FileNotFoundException если файл не существует.
     * @throws SecurityException если нет разрешений на чтение файла.
     * @throws IOException если произошла ошибка при чтении файла.
     */
    override fun countWordsInFile(pathName: String, word: String): Pair<Long, Long> {
        val file = openFile(pathName)
        val (totalWordsCount, wordCount) = file.countWords(word)
        return Pair(totalWordsCount, wordCount)
    }


    /**
     * Открывает файл по указанному пути.
     *
     * @param pathName Путь к файлу.
     * @return Объект [File], если файл существует и доступен для чтения, иначе `null`.
     * @throws FileNotFoundException Если файл не найден.
     * @throws IOException В случае ошибки ввода-вывода.
     * @throws SecurityException Если нет разрешения на чтение файла.
     */
    override fun openFile(pathName: String): File {
        val file = File(pathName)
        if (!file.exists() || !file.isFile) {
            throw FileNotFoundException("Файл '$pathName' не существует!")
        }
        if (!file.canRead()) {
            throw SecurityException("Нет разрешений на чтение файла '$pathName'!")
        }
        return file
    }

}