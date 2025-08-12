package com.matteogav.countries.data.repositories

import com.apollographql.apollo.ApolloClient
import com.matteogav.countries.GetContinentCountriesQuery
import com.matteogav.countries.GetContinentsQuery
import com.matteogav.countries.data.mappers.toDomain
import com.matteogav.countries.domain.models.Continent
import com.matteogav.countries.domain.models.Country
import com.matteogav.countries.domain.repositories.ContinentRepository

class ContinentRepositoryImpl(
    private val apolloClient: ApolloClient
) : ContinentRepository {
    override suspend fun getContinents(): List<Continent> {
        val response = apolloClient.query(GetContinentsQuery()).execute()
        return response.data.toDomain()
    }

    override suspend fun getContinentCountries(continentCode: String): List<Country> {
        val response = apolloClient.query(GetContinentCountriesQuery(continentCode)).execute()
        return response.data.toDomain()
    }
}