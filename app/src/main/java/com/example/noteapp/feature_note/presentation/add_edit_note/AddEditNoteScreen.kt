package com.example.noteapp.feature_note.presentation.add_edit_note

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.example.noteapp.feature_note.domain.model.Note
import com.example.noteapp.feature_note.presentation.add_edit_note.components.AttachmentDialog
import com.example.noteapp.feature_note.presentation.add_edit_note.components.TransparentHintTextField
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.io.File


@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = koinViewModel<AddEditNoteViewModel>(),
    userId: String
) {

    // Pass the userId to the ViewModel
    viewModel.setUserId(userId)

    // Retrieve the Context using LocalContext
    val context = LocalContext.current

    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val scaffoldState = rememberScaffoldState()
    val showDialog = remember { mutableStateOf(false) }

    val selectedFileUri = remember { mutableStateOf<Uri?>(null) }
    val selectedFileName = remember { mutableStateOf<String>("") }

    // Launchers for handling file, gallery, and camera actions
    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                viewModel.onEvent(AddEditNoteEvent.AttachDocument(uri))
            }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                viewModel.onEvent(AddEditNoteEvent.AttachImage(uri))
            }
        }
    )

//    val cameraLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.TakePicturePreview(),
//        onResult = { bitmap ->
//            if (bitmap != null) {
//                // Save or process bitmap as needed
//                val photoDescription = "Photo captured using camera"
//                val photoUri = saveBitmapToFileAndGetUri(context, bitmap) // Custom function to save bitmap and get URI
//                viewModel.onEvent(AddEditNoteEvent.AttachPhoto(photoDescription, photoUri))
//            }
//        }
//    )


    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(
                if (noteColor != -1) {
                    noteColor
                } else {
                    viewModel.noteColor.value
                }
            )
        )
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            Column(modifier = Modifier) {

                Icon(
                    Icons.Default.AttachFile,// Replace with your attachment icon
                    contentDescription = "Attach",
                    modifier = Modifier
                        .clickable {
                            // Handle attachment action
                            println("Attach Icon Clicked")
                            showDialog.value = true
                        }
                        .size(54.dp) // Adjust size as needed
                        .padding(4.dp),
                    tint = MaterialTheme.colors.onSurface
                )

                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(AddEditNoteEvent.SaveNote)
                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        Icons.Default.Save,
                        contentDescription = "save note",
                        tint = Color.Unspecified
                    )
                }

            }

        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(paddingValues)
                .padding(16.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.noteColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
                onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it)) },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = { viewModel.onEvent(AddEditNoteEvent.EnteredContent(it)) },
                onFocusChange = { viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it)) },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxHeight()
            )
        }

        if (showDialog.value) {
            AttachmentDialog(
                onDismissRequest = { showDialog.value = false },
                onOptionSelected = { selectedOption ->
                    showDialog.value = false
                    when (selectedOption) {
                        "Attach File" -> fileLauncher.launch(arrayOf("*/*"))
                        "Choose Photo or Video" -> galleryLauncher.launch("image/*")
                        "Take Photo or Video" -> {}
                    }
                }
            )
        }
    }
}

fun Uri.getFileContent(context: Context): ByteArray? {
    return try {
        context.contentResolver.openInputStream(this)?.use { inputStream ->
            inputStream.readBytes()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Uri.getFileName(context: Context): String {
    var name = ""
    context.contentResolver.query(this, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (cursor.moveToFirst()) {
            name = cursor.getString(nameIndex)
        }
    }
    return name
}

fun saveBitmapToFileAndGetUri(context: Context, bitmap: Bitmap): Uri {
    val file = File(context.cacheDir, "captured_image.png")
    file.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
}

