package com.matteogav.countries.features.countriesList

sealed interface CountriesListAction {
    data class OnContinentClicked(val continentCode: String) : CountriesListAction
    data class OnCountryClicked(val countryCode: String) : CountriesListAction
    data object OnCountryDialogDismissed : CountriesListAction
}