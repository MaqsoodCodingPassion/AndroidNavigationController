package com.servicenow.di.module

import com.servicenow.di.BaseUrl
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://jsonblob.com"
    }
}