import org.example.impl.FileManagerImpl
import org.example.impl.countWords
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class FileManagerTest {

    companion object {
        private lateinit var tempFile: File
        private lateinit var tempDir: File

        @JvmStatic
        @BeforeAll
        fun setup() {
            // Создаем временный файл с тестовыми данными
            tempFile = Files.createTempFile("testFile", ".txt").toFile()
            tempFile.writeText( "Hello world \nWorld of Kotlin \nhello World")

            // Создаем временную директорию
            tempDir = Files.createTempDirectory("testDir").toFile()
        }

        @JvmStatic
        @AfterAll
        fun cleanup() {
            // Удаляем временный файл и директорию
            tempFile.delete()
            tempDir.delete()
        }
    }

    @Test
    fun `test countWords should return correct word count`() {
        val (totalWordsCount, wordsCount) = tempFile.countWords("hello")
        assertEquals(7, totalWordsCount) // Общее количество слов
        assertEquals(2, wordsCount) // Количество вхождений слова "hello"
    }

    @Test
    fun `test countWords should return 0 for nonexistent word`() {
        val (totalWordsCount, wordsCount) = tempFile.countWords("java")
        assertEquals(7, totalWordsCount) // Общее количество слов
        assertEquals(0, wordsCount) // Количество вхождений слова "java"
    }

    @Test
    fun `test openFile should return file if exists`() {
        val file = FileManagerImpl().openFile(tempFile.absolutePath)
        assertEquals(tempFile, file) // Должен вернуть этот файл
    }

    @Test
    fun `test openFile should return null if file does not exist`() {
        val file = FileManagerImpl().openFile("nonexistent_file.txt")
        assertNull(file) // Должен вернуть null, файл не существует
    }

    @Test
    fun `test openFile should throw FileNotFoundException if file is a directory`() {
        assertFailsWith<FileNotFoundException> {
            FileManagerImpl().openFile(tempDir.absolutePath)
        }
    }


    // На винде не работает нормально
    @Test
    fun `test openFile should throw SecurityException if file not readable`() {
        Files.setAttribute(tempFile.toPath(), "dos:readonly", false)

        assertFailsWith<SecurityException> {
            FileManagerImpl().openFile(tempFile.path)
        }

        Files.setAttribute(tempFile.toPath(), "dos:readonly", true)
    }
}
