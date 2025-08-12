package com.matteogav.countries.features.countriesList

import androidx.compose.runtime.Stable
import com.matteogav.countries.domain.models.Continent
import com.matteogav.countries.features.countriesList.models.CountryInfoDialogStatus

@Stable
data class ContinentListState(
    val continents: List<Continent> = emptyList(),
    val expandedContinents: Set<String> = emptySet(),
    val countryInfoDialogStatus: CountryInfoDialogStatus = CountryInfoDialogStatus.Idle
)
