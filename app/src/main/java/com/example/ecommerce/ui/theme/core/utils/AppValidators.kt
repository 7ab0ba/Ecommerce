package com.example.ecommerce.ui.theme.core.utils


object AppValidators {
    //TODO:validate name
    fun validateName(name: String): String? {
        return if (name.trim().isEmpty()) "Please Enter Name" else
            null
        
    }
    
    //TODO:validate Mobile Number
    fun validatePhone(phone: String): String? {
       return when{
phone.trim().isEmpty()->"Please Enter Mobile Number"
            phone.length>11->"Mobile Number should be 11 digits"
            phone.length<11->"Mobile Number should be 11 digits"
            else -> null

        }
    }
    //TODO:validate email
    fun validateEmail(email: String): String? {
        val emailRegex="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return when{
            email.trim().isEmpty()->"Please Enter E-mail"
            !email.matches(emailRegex)->"Please Enter valid Email"
            else->null
        }

    }
    //TODO:validate password
    fun validatePassword(password: String): String? {
        val passwordRegex= "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$".toRegex()
        return when{
            password.trim().isEmpty()->"Please Enter Mobile Password"
!password.matches(passwordRegex)->"password should be at least 8 characters, one uppercase, one lowercase, one digit, and one special character"
            else -> null

        }
    }
}