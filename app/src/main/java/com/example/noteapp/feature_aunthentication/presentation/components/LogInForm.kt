package com.example.noteapp.feature_aunthentication.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LogInForm(
    onLoginClick: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val isFormValid = email.isNotEmpty() && password.isNotEmpty()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Email TextField
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email or Username",
            placeholder = "Enter your email",
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Password TextField
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "Enter your password",
            isPassword = true
        )
        Spacer(modifier = Modifier.height(34.dp))

        // Log-in Button
        CustomButton(
            text = "Log-in",
            onClick = {
                onLoginClick(email, password)
            },
            enabled = isFormValid
        )
    }
}


@Preview
@Composable
fun LogInFormPreview() {
    LogInForm(onLoginClick = { _, _ -> })
}


