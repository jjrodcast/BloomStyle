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
package com.example.androiddevchallenge.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.androiddevchallenge.R

object DataProvider {

    fun getPlantModelsHorizontal() = listOf(
        PlantModel(
            resId = R.drawable.desert_chic,
            text = "Desert chic"
        ),
        PlantModel(
            resId = R.drawable.tiny_terrariums,
            text = "Tiny terrariums"
        ),
        PlantModel(
            resId = R.drawable.jungle_vibes,
            text = "Jungle vibes"
        ),
        PlantModel(
            resId = R.drawable.easy_care,
            text = "Easy care"
        ),
        PlantModel(
            resId = R.drawable.statements,
            text = "Statements"
        )
    )

    fun getPlanModelsVertical() = listOf(
        PlantModel(
            resId = R.drawable.monstera,
            text = "Monstera",
            description = "This is a description"
        ),
        PlantModel(
            resId = R.drawable.aglaonema,
            text = "Aglaonema",
            description = "This is a description"
        ),
        PlantModel(
            resId = R.drawable.peace_lily,
            text = "Peace lily",
            description = "This is a description"
        ),
        PlantModel(
            resId = R.drawable.fiddle_leaf,
            text = "Fiddle leaf tree",
            description = "This is a description"
        ),
        PlantModel(
            resId = R.drawable.snake_plant,
            text = "Snake plant",
            description = "This is a description"
        ),
        PlantModel(
            resId = R.drawable.photos,
            text = "Photos",
            description = "This is a description"
        )
    )

    fun getOptionModels() = listOf(
        OptionModel(label = "Home", icon = Icons.Default.Home),
        OptionModel(label = "Favorites", icon = Icons.Default.FavoriteBorder),
        OptionModel(label = "Profile", icon = Icons.Default.AccountCircle),
        OptionModel(label = "Cart", icon = Icons.Default.ShoppingCart),
    )
}
