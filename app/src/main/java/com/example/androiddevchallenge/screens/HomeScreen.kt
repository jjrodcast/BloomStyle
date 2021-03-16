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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.models.DataProvider
import com.example.androiddevchallenge.models.OptionModel
import com.example.androiddevchallenge.models.PlantModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.google.accompanist.coil.CoilImage

@Composable
fun HomeScreen(navController: NavController) {
    var search by remember { mutableStateOf("") }
    var selectedBottomItem by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            BloomOutlineTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier.padding(horizontal = 16.dp),
                placeholder = { BloomTextPlaceholder(stringResource(R.string.search)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = stringResource(R.string.search),
                        modifier = Modifier.size(18.dp)
                    )
                }
            )
        },
        bottomBar = {
            BloomBottomNavigation(DataProvider.getOptionModels(), selectedBottomItem) {
                selectedBottomItem = it
            }
        }

    ) {
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = stringResource(R.string.browse_themes_title),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.paddingFromBaseline(top = 32.dp)
            )
            BloomBrowseList(DataProvider.getPlantModelsHorizontal())
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.design_home_title),
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.paddingFromBaseline(top = 40.dp)
                    )
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .width(24.dp)
                            .paddingFromBaseline(top = 12.dp)
                    ) {
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = null,
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                }
            }
            BloomGardenList(items = DataProvider.getPlanModelsVertical())
        }
    }
}

@Composable
fun BloomBottomNavigation(
    items: List<OptionModel>,
    selectedItem: Int,
    onSelectedChange: (Int) -> Unit
) {
    BottomNavigation(backgroundColor = MaterialTheme.colors.primary) {
        items.mapIndexed { index, item ->
            BottomNavigationItem(
                selected = index == selectedItem,
                onClick = { onSelectedChange(index) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                selectedContentColor = MaterialTheme.colors.onBackground,
                label = { Text(item.label) }
            )
        }
    }
}

@Composable
fun BloomGardenList(items: List<PlantModel>) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 58.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            BloomGardenItem(item, index == 0)
            Divider(startIndent = 70.dp)
        }
    }
}

@Composable
fun BloomGardenItem(item: PlantModel, isChecked: Boolean) {
    Row(
        Modifier
            .clickable { }
            .fillMaxWidth()
            .height(64.dp)
    ) {
        CoilImage(
            data = item.resId,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            fadeIn = true,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .size(64.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = item.text,
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.paddingFromBaseline(top = 24.dp)
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.paddingFromBaseline(bottom = 24.dp)
                    )
                }
                Column(
                    Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 4.dp)
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { },
                        colors = CheckboxDefaults.colors(checkmarkColor = MaterialTheme.colors.onSecondary)
                    )
                }
            }
        }
    }
}

@Composable
fun BloomBrowseList(itemsPlant: List<PlantModel>) {
    LazyRow(
        contentPadding = PaddingValues(top = 8.dp, bottom = 2.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(itemsPlant) { _, item ->
            BloomBrowseItem(item)
        }
    }
}

@Composable
fun BloomBrowseItem(item: PlantModel) {
    Card(
        modifier = Modifier
            .size(136.dp),
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .clickable { }
                .background(MaterialTheme.colors.surface)
        ) {
            CoilImage(
                data = item.resId,
                contentDescription = item.text,
                fadeIn = true,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    item.text,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeLightPreview() {
    MyTheme {
        HomeScreen(rememberNavController())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeDarkPreview() {
    MyTheme(darkTheme = true) {
        HomeScreen(rememberNavController())
    }
}
