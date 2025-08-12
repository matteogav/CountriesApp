package com.matteogav.countries.data.mappers

import com.matteogav.countries.GetContinentCountriesQuery
import com.matteogav.countries.GetContinentsQuery
import com.matteogav.countries.domain.models.Continent
import com.matteogav.countries.domain.models.Country
import com.matteogav.countries.domain.utils.PLUS

fun GetContinentsQuery.Data?.toDomain(): List<Continent> =
    this?.continents?.map { continent ->
        Continent(
            id = continent.code,
            name = continent.name,
            countries = listOf()
        )
    } ?: emptyList()

fun GetContinentCountriesQuery.Data?.toDomain(): List<Country> =
    this?.continent?.countries?.map { country ->
        Country(
            id = country.code,
            name = country.name,
            phonePrefix = mapPhonePrefix(country.phone),
            flag = getEmoji(country.emojiU),
            currency = country.currency
        )
    } ?: emptyList()


private fun mapPhonePrefix(prefix: String): String {
    return PLUS.plus(prefix)
}

private fun getEmoji(code: String): String {
    return code.split(" ")
        .map { it.removePrefix("U+").toInt(16) }
        .map { Character.toChars(it) }
        .joinToString("") { String(it) }
}