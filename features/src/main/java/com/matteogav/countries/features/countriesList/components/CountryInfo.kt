package com.matteogav.countries.features.countriesList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matteogav.countries.features.R
import com.matteogav.countries.features.utils.purplePrimary

@Composable
fun CountryInfo(
    name: String,
    flag: String,
    phonePrefix: String,
    currency: String?,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .border(width = 2.dp, color = purplePrimary, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 24.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 18.dp),
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = purplePrimary,
                textAlign = TextAlign.Center
            )
            Text(
                text = flag,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 18.dp),
            text = stringResource(R.string.phone_prefix, phonePrefix),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = purplePrimary,
            textAlign = TextAlign.Center
        )
        currency?.let {
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 18.dp),
                text = stringResource(R.string.currency, currency),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = purplePrimary,
                textAlign = TextAlign.Center
            )
        }
        Button(
            modifier = Modifier
                .padding(horizontal = 48.dp, vertical = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonColors(
                containerColor = purplePrimary,
                contentColor = Color.White,
                disabledContentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified
            ),
            onClick = { onDismiss() }
        ) {
            Text(
                text = stringResource(R.string.close),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}