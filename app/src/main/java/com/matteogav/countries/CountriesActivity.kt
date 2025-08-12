package com.matteogav.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.matteogav.countries.features.countriesList.CountriesListScreen
import com.matteogav.countries.ui.theme.CountriesAppTheme
import org.koin.compose.KoinContext

class CountriesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    KoinContext {
                        CountriesListScreen()
                    }
                }
            }
        }
    }
}