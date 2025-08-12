package com.matteogav.countries.features.countriesList.components

import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import com.matteogav.countries.features.countriesList.models.CountryInfoDialogStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryInfoDialog(
    modifier: Modifier = Modifier,
    dialogStatus: CountryInfoDialogStatus,
    onDismiss: () -> Unit
) {
    when(dialogStatus) {
        is CountryInfoDialogStatus.CountryInfo -> {
            BasicAlertDialog(
                modifier = modifier,
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                ),
                onDismissRequest = onDismiss
            ) {
                val country = dialogStatus.country
                CountryInfo(
                    name = country.name,
                    flag = country.flag,
                    phonePrefix = country.phonePrefix,
                    currency = country.currency
                ) {
                    onDismiss()
                }
            }
        }
        else -> return
    }
}