package com.matteogav.countries.domain.di

import com.matteogav.countries.domain.usecases.GetContinentCountriesUseCase
import com.matteogav.countries.domain.usecases.GetContinentsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetContinentsUseCase)
    factoryOf(::GetContinentCountriesUseCase)
}