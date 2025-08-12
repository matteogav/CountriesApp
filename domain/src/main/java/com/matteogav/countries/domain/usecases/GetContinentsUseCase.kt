package com.matteogav.countries.domain.usecases

import com.matteogav.countries.domain.repositories.ContinentRepository

class GetContinentsUseCase(
    private val continentRepository: ContinentRepository
) {
    suspend operator fun invoke() = continentRepository.getContinents()
}