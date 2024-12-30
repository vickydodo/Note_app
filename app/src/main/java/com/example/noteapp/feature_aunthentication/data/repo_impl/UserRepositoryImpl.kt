package com.example.noteapp.feature_aunthentication.data.repo_impl

import com.example.noteapp.feature_aunthentication.data.data_source.UserDao
import com.example.noteapp.feature_aunthentication.domain.model.User
import com.example.noteapp.feature_aunthentication.domain.repo.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun signInWithGoogle(idToken: String) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        Firebase.auth.signInWithCredential(firebaseCredential).await()
    }


}