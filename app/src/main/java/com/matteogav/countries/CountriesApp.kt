package com.matteogav.countries

import android.app.Application
import com.matteogav.countries.data.di.dataModule
import com.matteogav.countries.domain.di.domainModule
import com.matteogav.countries.features.di.featuresModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CountriesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CountriesApp)

            modules(
                dataModule,
                domainModule,
                featuresModule
            )
        }
    }
}