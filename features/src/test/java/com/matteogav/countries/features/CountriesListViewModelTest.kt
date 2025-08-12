package com.matteogav.countries.features

import com.matteogav.countries.domain.models.Country
import com.matteogav.countries.domain.usecases.GetContinentCountriesUseCase
import com.matteogav.countries.domain.usecases.GetContinentsUseCase
import com.matteogav.countries.features.countriesList.CountriesListAction
import com.matteogav.countries.features.countriesList.CountriesListViewModel
import com.matteogav.countries.features.countriesList.models.CountryInfoDialogStatus
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class CountriesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getContinentsUseCase: GetContinentsUseCase
    private lateinit var getContinentCountriesUseCase: GetContinentCountriesUseCase
    private lateinit var sut: CountriesListViewModel

    @Before
    fun setup() {
        getContinentsUseCase = mockk()
        getContinentCountriesUseCase = mockk()

        coEvery { getContinentsUseCase.invoke() } returns listOf(dummyContinent, dummyContinentWithoutCountries)
        coEvery { getContinentCountriesUseCase.invoke("AS") } returns listOf(
            Country(
                id = "JP",
                name = "Japan",
                phonePrefix = "+81",
                flag = "🇯🇵",
                currency = "JPY"
            )
        )

        sut = CountriesListViewModel(getContinentsUseCase, getContinentCountriesUseCase)
    }

    @Test
    fun `init loads continents`() = runTest {
        advanceUntilIdle()

        val state = sut.state.value
        assertEquals(2, state.continents.size)
        assertTrue(state.continents.any { it.id == "EU" })
        assertTrue(state.continents.any { it.id == "AS" })
    }

    @Test
    fun `onContinentClicked toggles expandedContinents and loads countries if empty`() = runTest {
        advanceUntilIdle()

        assertTrue(sut.state.value.expandedContinents.isEmpty())

        sut.onAction(CountriesListAction.OnContinentClicked("EU"))

        var state = sut.state.value
        assertTrue(state.expandedContinents.contains("EU"))
        assertEquals(1, state.continents.find { it.id == "EU" }?.countries?.size)

        sut.onAction(CountriesListAction.OnContinentClicked("AS"))
        advanceUntilIdle()

        state = sut.state.value
        assertTrue(state.expandedContinents.contains("AS"))
        val countriesInAS = state.continents.find { it.id == "AS" }?.countries ?: emptyList()
        assertEquals(1, countriesInAS.size)
        assertEquals("JP", countriesInAS[0].id)

        sut.onAction(CountriesListAction.OnContinentClicked("AS"))
        state = sut.state.value
        assertFalse(state.expandedContinents.contains("AS"))
    }

    @Test
    fun `onCountryClicked sets countryInfoDialogStatus when Idle`() = runTest {
        advanceUntilIdle()

        assertEquals(CountryInfoDialogStatus.Idle, sut.state.value.countryInfoDialogStatus)

        sut.onAction(CountriesListAction.OnCountryClicked("ES"))
        val status = sut.state.value.countryInfoDialogStatus
        assertTrue(status is CountryInfoDialogStatus.CountryInfo)
        assertEquals("ES", (status as CountryInfoDialogStatus.CountryInfo).country.id)
    }

    @Test
    fun `onCountryClicked does nothing if country not found`() = runTest {
        advanceUntilIdle()

        sut.onAction(CountriesListAction.OnCountryClicked("NON_EXISTENT_COUNTRY"))

        assertEquals(CountryInfoDialogStatus.Idle, sut.state.value.countryInfoDialogStatus)
    }

    @Test
    fun `onCountryDialogDismiss sets countryInfoDialogStatus to Idle`() = runTest {
        advanceUntilIdle()

        sut.onAction(CountriesListAction.OnCountryClicked("ES"))
        assertTrue(sut.state.value.countryInfoDialogStatus is CountryInfoDialogStatus.CountryInfo)

        sut.onAction(CountriesListAction.OnCountryDialogDismissed)

        assertEquals(CountryInfoDialogStatus.Idle, sut.state.value.countryInfoDialogStatus)
    }
}