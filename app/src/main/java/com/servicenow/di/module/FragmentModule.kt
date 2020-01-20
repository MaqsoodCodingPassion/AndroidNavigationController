package com.servicenow.di.module

import com.servicenow.fragments.DescriptionDetailsFragment
import com.servicenow.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDescriptionDetailsFragment(): DescriptionDetailsFragment
}