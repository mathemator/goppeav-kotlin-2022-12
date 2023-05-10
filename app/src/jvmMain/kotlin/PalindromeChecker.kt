package ru.otus.goppeav.main

class PalindromeChecker {

    fun isPalindrome(input: String) : Boolean {
        return input.reversed().equals(input)

    }
}