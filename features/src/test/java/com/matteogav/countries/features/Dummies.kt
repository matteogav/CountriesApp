package com.matteogav.countries.features

import com.matteogav.countries.domain.models.Continent
import com.matteogav.countries.domain.models.Country

val dummyCountry = Country(
    id = "ES",
    name = "Spain",
    phonePrefix = "+34",
    flag = "spanishFlag",
    currency = "EUR"
)

val dummyContinent = Continent(
    id = "EU",
    name = "Europe",
    countries = listOf(dummyCountry)
)

val dummyContinentWithoutCountries = Continent(
    id = "AS",
    name = "Asia",
    countries = emptyList()
)
