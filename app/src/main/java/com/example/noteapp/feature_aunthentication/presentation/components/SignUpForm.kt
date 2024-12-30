package com.example.noteapp.feature_aunthentication.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignUpForm(onSignUpClick: (String, String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }


    val isFormValid = emailError == null && passwordError == null &&
            confirmPasswordError == null && email.isNotEmpty() && password.isNotEmpty()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
    ) {
        CustomTextField(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            placeholder = "Enter your username",
            isPassword = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = if (email.isValidEmail()) null else "Invalid email address"
            },
            label = "Email",
            placeholder = "Enter your email",
            isPassword = false,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )
        emailError?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError =
                    if (password.isValidPassword()) null else "Password must be at least 8 characters long"
            },
            label = "Password",
            placeholder = "Enter your password",
            isPassword = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            }

        )
        passwordError?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError =
                    if (confirmPassword == password) null else "Passwords do not match"
            },
            label = "Confirm Password",
            isPassword = true,
            placeholder = "Confirm your password",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            }

        )
        confirmPasswordError?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
        }
        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            text = "Sign Up",
            onClick = { onSignUpClick(username, email, password) },
            enabled = isFormValid
        )
        Spacer(modifier = Modifier.height(24.dp))

    }
}

@Preview
@Composable
fun SignUpFormPreview() {
    SignUpForm(onSignUpClick = { _, _, _ -> })
}