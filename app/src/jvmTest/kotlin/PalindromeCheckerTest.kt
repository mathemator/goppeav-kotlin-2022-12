import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import ru.otus.goppeav.main.PalindromeChecker

class PalindromeCheckerTest {

    val palindromeChecker: PalindromeChecker = PalindromeChecker()

    @ParameterizedTest
    @ValueSource(strings = ["131", "abcddcba"])
    fun `isPalindrome`(input: String) {
        assertTrue { palindromeChecker.isPalindrome(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["132", "abcddcbz"])
    fun `isNotPalindrome`(input: String) {
        assertFalse { palindromeChecker.isPalindrome(input) }
    }
}