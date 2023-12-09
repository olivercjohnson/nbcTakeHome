package com.robonouveau.nbc.takehome.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.robonouveau.nbc.takehome.viewmodels.ShelfScreenViewModel

@Composable
fun HomepageScreen(
    modifier: Modifier = Modifier,
    shelfScreenViewModel: ShelfScreenViewModel = hiltViewModel(),
) {
    val shelves by shelfScreenViewModel.shelves().collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        LaunchedEffect(key1 = "asdf") {
            shelfScreenViewModel.refreshScreen()
        }

        LazyColumn(
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            items(shelves) { shelf ->

                ShelfGroup(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shelf = shelf
                )
            }
        }
    }

}
