package com.servicenow.di.component

import com.servicenow.GamesApplication
import com.servicenow.di.module.ActivityBuilder
import com.servicenow.di.module.ApplicationModule
import com.servicenow.di.module.FragmentModule
import com.servicenow.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (NetworkModule::class),
        (ApplicationModule::class),
        (ActivityBuilder::class),
        (FragmentModule::class)]
)

interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: GamesApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: GamesApplication)
}
