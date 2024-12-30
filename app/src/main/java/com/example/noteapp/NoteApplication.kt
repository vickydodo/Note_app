package com.example.noteapp

import android.app.Application
import com.example.noteapp.di.noteDatabaseModule
import com.example.noteapp.di.noteRepositoryModule
import com.example.noteapp.di.noteUseCaseModule
import com.example.noteapp.di.noteViewModelModule
import com.example.noteapp.di.userRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NoteApplication)
            modules(listOf(
                noteDatabaseModule,
                noteRepositoryModule,
                userRepositoryModule,
                noteViewModelModule,
                noteUseCaseModule
            ))
        }
    }
}