package com.matteogav.countries.domain.usecases

import com.matteogav.countries.domain.repositories.ContinentRepository

class GetContinentCountriesUseCase(
    private val continentRepository: ContinentRepository
) {
    suspend operator fun invoke(continentCode: String) = continentRepository.getContinentCountries(continentCode)
}