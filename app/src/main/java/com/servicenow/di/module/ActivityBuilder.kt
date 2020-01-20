package com.servicenow.di.module

import com.servicenow.exercise_java.MainActivity
import com.servicenow.exercise_kotlin.MainActivityKt
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindActivityMain(): MainActivityKt
}