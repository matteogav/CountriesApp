package com.matteogav.countries.features.di

import com.matteogav.countries.features.countriesList.CountriesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featuresModule = module {
    viewModelOf(::CountriesListViewModel)
}