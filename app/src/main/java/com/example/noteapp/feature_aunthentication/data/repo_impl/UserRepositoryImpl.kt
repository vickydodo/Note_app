package com.example.noteapp.feature_aunthentication.data.repo_impl

import com.example.noteapp.feature_aunthentication.domain.repo.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl : UserRepository {

    override suspend fun signInWithGoogle(idToken: String) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        Firebase.auth.signInWithCredential(firebaseCredential).await()
    }


}