import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import ru.otus.goppeav.main.PalindromeChecker

class PalindromeCheckerKotest : FreeSpec() {

    init {
        "Scenario. Single case" - {

            lateinit var palindromeChecker: PalindromeChecker
            var result = false
            //endregion

            "Given palindromeChecker initialized" {
                palindromeChecker = PalindromeChecker()
            }

            "When we ask him to check palindorme" {
                result = palindromeChecker.isPalindrome("131")
            }

            "Then result is positive" {
                result shouldBe true
            }
        }
    }
}