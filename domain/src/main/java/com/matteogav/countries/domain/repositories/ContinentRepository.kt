package com.matteogav.countries.domain.repositories

import com.matteogav.countries.domain.models.Continent
import com.matteogav.countries.domain.models.Country

interface ContinentRepository {
    suspend fun getContinents() : List<Continent>
    suspend fun getContinentCountries(continentCode: String) : List<Country>
}