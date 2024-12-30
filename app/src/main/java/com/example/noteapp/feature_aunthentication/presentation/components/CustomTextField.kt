package com.example.noteapp.feature_aunthentication.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    leadingIcon: (@Composable (() -> Unit))? = null // Nullable parameter for the icon

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.h5.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(width = 2.dp, color = Color(0xFFB0C4FF)),
                    shape = RoundedCornerShape(50)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = leadingIcon
        )
    }
}

@Preview
@Composable
fun CustomTextFieldPreview() {
    CustomTextField(
        label = "Email",
        value = "Email",
        onValueChange = {},
        placeholder = "Enter your email",
        leadingIcon = {}
    )
}