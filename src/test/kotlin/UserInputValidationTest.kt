import org.example.isUserInputInvalid
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class UserInputValidationTest {

    @Test
    fun `should return true for null path`() {
        assertTrue(isUserInputInvalid(null, "hello"))
    }

    @Test
    fun `should return true for null word`() {
        assertTrue(isUserInputInvalid("file.txt", null))
    }

    @Test
    fun `should return true for empty path`() {
        assertTrue(isUserInputInvalid("", "hello"))
    }

    @Test
    fun `should return true for empty word`() {
        assertTrue(isUserInputInvalid("file.txt", ""))
    }

    @Test
    fun `should return true for blank path`() {
        assertTrue(isUserInputInvalid("   ", "hello"))
    }

    @Test
    fun `should return true for blank word`() {
        assertTrue(isUserInputInvalid("file.txt", "   "))
    }

    @Test
    fun `should return false for valid inputs`() {
        assertFalse(isUserInputInvalid("file.txt", "hello"))
    }
}