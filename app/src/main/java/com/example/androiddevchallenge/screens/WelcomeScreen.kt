/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.navigator.Routes
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.utils.ResourcesProvider

@Composable
fun WelcomeScreen(navController: NavController) {
    Surface(
        Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Image(
            painter = painterResource(ResourcesProvider.getBackground(isSystemInDarkTheme())),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 72.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .offset(x = 80.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(ResourcesProvider.getImage(isSystemInDarkTheme())),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
            )
            Image(
                painter = painterResource(ResourcesProvider.getLogo(isSystemInDarkTheme())),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.welcome_subtitle),
                modifier = Modifier
                    .height(72.dp)
                    .paddingFromBaseline(24.dp),
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.subtitle1
            )
            BloomButton(
                onClick = { },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                buttonText = stringResource(R.string.create_account)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            BloomTextButton(
                onClick = { navController.navigate(Routes.Login) },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                buttonText = stringResource(R.string.log_in)
            )
        }
    }
}

@Composable
fun BloomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
    ) {
        Text(text = buttonText, style = MaterialTheme.typography.button)
    }
}

@Composable
fun BloomTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String
) {
    TextButton(
        onClick = onClick, modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.secondaryVariant
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun WelcomeLightPreview() {
    MyTheme {
        WelcomeScreen(rememberNavController())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun WelcomeDarkPreview() {
    MyTheme(darkTheme = true) {
        WelcomeScreen(rememberNavController())
    }
}
