package com.matteogav.countries.domain.models

data class Country(
    val id: String,
    val name: String,
    val phonePrefix: String,
    val flag: String,
    val currency: String?
)
