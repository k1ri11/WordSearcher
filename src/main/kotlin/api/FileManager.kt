package org.example.api

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Интерфейс класса для подсчета слов в файле.
 */
interface FileManager {

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
    fun countWordsInFile(pathName: String, word: String): Pair<Long, Long>

    /**
     * Открывает файл по указанному пути.
     *
     * @param pathName Путь к файлу.
     * @return Объект [File], если файл существует и доступен для чтения, иначе `null`.
     * @throws FileNotFoundException Если файл не найден.
     * @throws IOException В случае ошибки ввода-вывода.
     * @throws SecurityException Если нет разрешения на чтение файла.
     */
    fun openFile(pathName: String): File

}