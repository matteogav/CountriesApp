package com.matteogav.countries.features.countriesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matteogav.countries.domain.models.Country
import com.matteogav.countries.domain.usecases.GetContinentCountriesUseCase
import com.matteogav.countries.domain.usecases.GetContinentsUseCase
import com.matteogav.countries.features.countriesList.models.CountryInfoDialogStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountriesListViewModel(
    private val getContinentsUseCase: GetContinentsUseCase,
    private val getContinentCountriesUseCase: GetContinentCountriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ContinentListState())
    val state: StateFlow<ContinentListState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    continents = getContinentsUseCase.invoke()
                )
            }
        }
    }

    fun onAction(action: CountriesListAction) {
        when(action) {
            is CountriesListAction.OnContinentClicked -> onContinentClicked(action.continentCode)
            is CountriesListAction.OnCountryClicked -> onCountryClicked(action.countryCode)
            CountriesListAction.OnCountryDialogDismissed -> onCountryDialogDismiss()
        }
    }

    private fun onContinentClicked(code: String) {
        _state.value.continents.find { it.id == code }?.let { continent ->
            if (continent.countries.isEmpty()) {
                viewModelScope.launch {
                    val countries = getContinentCountriesUseCase.invoke(code)
                    val updatedContinents = _state.value.continents.map {
                        if (it.id == code) it.copy(countries = countries)
                        else it
                    }

                    _state.update { state ->
                       state.copy(continents = updatedContinents)
                    }
                }
            }
            _state.update { state ->
                state.copy(
                    expandedContinents = state.expandedContinents.toMutableSet().apply {
                        if (contains(code)) remove(code) else add(code)
                    }
                )
            }
        }
    }

    private fun onCountryClicked(code: String) {
        val countrySelected = findCountryById(code) ?: return
        if(state.value.countryInfoDialogStatus == CountryInfoDialogStatus.Idle) {
            _state.update { state ->
                state.copy(
                    countryInfoDialogStatus = CountryInfoDialogStatus.CountryInfo(countrySelected)
                )
            }
        }
    }

    private fun onCountryDialogDismiss() {
        _state.update { state ->
            state.copy(
                countryInfoDialogStatus = CountryInfoDialogStatus.Idle
            )
        }
    }

    private fun findCountryById(code: String): Country? {
        return state.value.continents
            .asSequence()
            .flatMap { it.countries.asSequence() }
            .firstOrNull { it.id == code }
    }
}