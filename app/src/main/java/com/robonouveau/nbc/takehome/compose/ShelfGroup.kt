package com.robonouveau.nbc.takehome.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.robonouveau.nbc.takehome.data.Episode
import com.robonouveau.nbc.takehome.data.Live
import com.robonouveau.nbc.takehome.data.Shelf
import com.robonouveau.nbc.takehome.data.Show

@Composable
fun ShelfGroup(
    modifier: Modifier,
    shelf: Shelf
) {
    Column(modifier = modifier) {

        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleLarge,
            text = shelf.title,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
        ) {
            items(shelf.items) { shelfItem ->
                Box(
                    modifier = Modifier.height(240.dp).padding(8.dp),
                ) {
                    when (shelfItem) {
                        is Episode -> EpisodeShelfItem(Modifier, shelfItem)
                        is Live -> LiveShelfItem(Modifier, shelfItem)
                        is Show -> ShowShelfItem(Modifier, shelfItem)
                    }
                }
            }
        }
    }
}
