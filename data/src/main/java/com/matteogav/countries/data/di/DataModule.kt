package com.matteogav.countries.data.di

import com.apollographql.apollo.ApolloClient
import com.matteogav.countries.domain.repositories.ContinentRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.matteogav.countries.data.repositories.ContinentRepositoryImpl
import org.koin.dsl.bind

val dataModule = module {
    single {
        ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    singleOf(::ContinentRepositoryImpl) bind ContinentRepository::class
}