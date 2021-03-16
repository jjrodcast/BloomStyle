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

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.navigator.Routes
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.login_with_email),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .paddingFromBaseline(top = 184.dp)
        )
        BloomOutlineTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { BloomTextPlaceholder(stringResource(R.string.email_address_placeholder)) }
        )
        Spacer(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        BloomOutlineTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { BloomTextPlaceholder(stringResource(R.string.password_placeholder)) }
        )
        BloomTextTermsFirst(
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
        )
        BloomButton(
            onClick = { navController.navigate(Routes.Home) },
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            buttonText = stringResource(R.string.log_in)
        )
    }
}

@Composable
fun BloomOutlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: () -> Boolean = { false },
    placeholder: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        isError = isError(),
        visualTransformation = visualTransformation,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    )
}

@Composable
fun BloomTextPlaceholder(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun BloomTextTermsFirst(modifier: Modifier = Modifier) {
    val spanStyle = SpanStyle(textDecoration = TextDecoration.Underline)
    Text(
        buildAnnotatedString {
            append(stringResource(R.string.terms_condition_first))
            withStyle(style = spanStyle) {
                append(stringResource(R.string.terms_of_use))
            }
            append(stringResource(R.string.terms_condition_second))
            withStyle(style = spanStyle) {
                append(stringResource(R.string.privacy_policy))
            }
        },
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onPrimary,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginLightPreview() {
    MyTheme {
        LoginScreen(rememberNavController())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginDarkPreview() {
    MyTheme(darkTheme = true) {
        LoginScreen(rememberNavController())
    }
}
