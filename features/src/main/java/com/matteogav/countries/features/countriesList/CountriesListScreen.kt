package com.matteogav.countries.features.countriesList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matteogav.countries.features.countriesList.components.ContinentListItem
import com.matteogav.countries.features.countriesList.components.CountryInfoDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountriesListScreen(
    viewModel: CountriesListViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()
    val onAction by remember {
        mutableStateOf<(CountriesListAction) -> Unit>({ action ->
            viewModel.onAction(action)
        })
    }

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.continents) { continent ->
                ContinentListItem(
                    name = continent.name,
                    countries = continent.countries,
                    isExpanded = state.expandedContinents.contains(continent.id),
                    onClickedContinent = { onAction(CountriesListAction.OnContinentClicked(continent.id)) },
                    onClickedCountry = { onAction(CountriesListAction.OnCountryClicked(it)) }
                )
            }
        }

        CountryInfoDialog(
            dialogStatus = state.countryInfoDialogStatus,
            onDismiss = { onAction(CountriesListAction.OnCountryDialogDismissed) }
        )
    }
}