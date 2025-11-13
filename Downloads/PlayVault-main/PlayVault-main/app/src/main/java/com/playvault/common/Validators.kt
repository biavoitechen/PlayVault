package com.playvault.common

object Validators {
    fun emailOk(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(regex)
    }
    fun passwordOk(password: String): Boolean = password.length >= 6
}
