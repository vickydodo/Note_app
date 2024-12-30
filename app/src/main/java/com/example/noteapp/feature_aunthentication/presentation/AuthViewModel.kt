package com.example.noteapp.feature_aunthentication.presentation

import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.feature_aunthentication.domain.model.User
import com.example.noteapp.feature_aunthentication.domain.use_case.AddUserUseCase
import com.example.noteapp.feature_note.utils.ERROR_TAG
import com.example.noteapp.feature_note.utils.UNEXPECTED_CREDENTIAL
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel(
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>(AuthState.Loading)
    val authState: LiveData<AuthState> = _authState

    // Observable User ID
    private val _userId = MutableLiveData<String?>()
    val userId: LiveData<String?> = _userId

    init {
        checkAuthState()
    }

    private fun checkAuthState() {
        val currentUser = auth.currentUser

        if (currentUser == null) {
            _authState.value = AuthState.UnAuthenticated
            _userId.value = null
        } else {
            _authState.value = AuthState.Authenticated
            _userId.value = currentUser.uid
        }

    }

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) {
                _authState.value = AuthState.Error("Email and password can't be blank ")
                return@launch
            }
            _authState.value = AuthState.Loading
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                        _userId.value = auth.currentUser?.uid
                    } else {
                        _authState.value =
                            AuthState.Error(task.exception?.message ?: "Something went wrong")
                    }
                }
        }
    }

    fun signUp(username: String, email: String, password: String) {
        viewModelScope.launch {

            if (email.isBlank() || password.isBlank()) {
                _authState.value = AuthState.Error("Email and password can't be blank ")
                return@launch
            }
            _authState.value = AuthState.Loading

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                        _userId.value = auth.currentUser?.uid // Update user ID
                        addUserToDatabase(username, email) // Add user to the database
                    } else {
                        _authState.value =
                            AuthState.Error(task.exception?.message ?: "Something went wrong")
                    }
                }
        }
    }

    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
        _userId.value = null // Clear user ID

    }

    private fun addUserToDatabase(username: String, email: String) {
        val uid = auth.currentUser?.uid ?: return // Firebase UID should not be null here
        val userId = uid.hashCode() // Or use a more robust ID mapping strategy
        viewModelScope.launch {
            try {
                addUserUseCase(
                    User(
                        id = userId,
                        uid = uid,
                        name = username,
                        email = email
                    )
                )
            } catch (e: Exception) {
                _authState.postValue(AuthState.Error("Failed to save user to database."))
            }
        }
    }

    fun onSignInWithGoogle(credential: Credential) {
        viewModelScope.launch {
            try {
                _authState.value = AuthState.Loading

                if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)
                    addUserUseCase(googleIdTokenCredential.idToken)

                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        _authState.value = AuthState.Authenticated
                        _userId.value = currentUser.uid
                    } else {
                        _authState.value = AuthState.Error("Authentication failed")
                    }
                } else {
                    Log.e(ERROR_TAG, UNEXPECTED_CREDENTIAL)
                    _authState.value = AuthState.Error("Unexpected credential type")
                }
            } catch (e: Exception) {
                Log.e(ERROR_TAG, e.message ?: "Unknown error occurred")
                _authState.value = AuthState.Error("Google Sign-In failed: ${e.message}")
            }
        }
    }
}


sealed class AuthState {

    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}