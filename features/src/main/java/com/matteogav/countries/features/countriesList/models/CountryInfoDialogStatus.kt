package com.matteogav.countries.features.countriesList.models

import com.matteogav.countries.domain.models.Country

sealed interface CountryInfoDialogStatus {
    data object Idle : CountryInfoDialogStatus
    data class CountryInfo(val country: Country) : CountryInfoDialogStatus
}