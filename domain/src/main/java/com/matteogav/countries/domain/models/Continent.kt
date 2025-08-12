package com.matteogav.countries.domain.models

data class Continent(
    val id: String,
    val name: String,
    val countries: List<Country>
)
