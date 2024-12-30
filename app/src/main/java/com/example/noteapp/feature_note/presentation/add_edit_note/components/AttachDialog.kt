package com.example.noteapp.feature_note.presentation.add_edit_note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AttachmentDialog(onDismissRequest: () -> Unit, onOptionSelected: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)) // Semi-transparent background
            .clickable(onClick = onDismissRequest)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            color = MaterialTheme.colors.surface
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OptionItem(
                    "Attach File",
                    com.example.noteapp.R.drawable.ic_attach_file,
                    onOptionSelected
                )
                OptionItem(
                    "Choose Photo or Video",
                    com.example.noteapp.R.drawable.ic_photo_video,
                    onOptionSelected
                )
                OptionItem(
                    "Take Photo or Video",
                    com.example.noteapp.R.drawable.ic_take_photo,
                    onOptionSelected
                )
            }
        }
    }
}

@Composable
fun OptionItem(optionText: String, iconResId: Int, onOptionSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOptionSelected(optionText) }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconResId),
            contentDescription = optionText,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = optionText,
            fontSize = 16.sp,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview
@Composable
fun AttachDialogPreview() {
    AttachmentDialog(onDismissRequest = {}, onOptionSelected = {})
}
