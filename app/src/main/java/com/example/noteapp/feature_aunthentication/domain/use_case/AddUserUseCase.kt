package com.example.noteapp.feature_aunthentication.domain.use_case

import com.example.noteapp.feature_aunthentication.domain.model.User
import com.example.noteapp.feature_aunthentication.domain.repo.UserRepository

class AddUserUseCase (
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.insertUser(user)
    }

    suspend operator fun invoke(idToken: String) {
        userRepository.signInWithGoogle(idToken)
    }

}