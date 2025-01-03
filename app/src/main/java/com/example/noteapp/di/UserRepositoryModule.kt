package com.example.noteapp.di

import com.example.noteapp.feature_aunthentication.data.repo_impl.UserRepositoryImpl
import com.example.noteapp.feature_aunthentication.domain.repo.UserRepository
import org.koin.dsl.module

val userRepositoryModule = module {
    single<UserRepository> { UserRepositoryImpl() }
}

