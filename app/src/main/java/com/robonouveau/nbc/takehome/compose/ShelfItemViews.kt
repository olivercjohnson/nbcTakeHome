package com.robonouveau.nbc.takehome.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.robonouveau.nbc.takehome.data.Episode
import com.robonouveau.nbc.takehome.data.LabelBadge
import com.robonouveau.nbc.takehome.data.Live
import com.robonouveau.nbc.takehome.data.Show

@Composable
fun LabelBadgeOverlay(
    modifier: Modifier,
    labelBadge: LabelBadge
) {
    when (labelBadge) {
        LabelBadge.NEW -> {
            Box(
                modifier = modifier
                    .background(Color.White)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = "NEW",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }

        LabelBadge.EXPIRING -> {
            Box(
                modifier = modifier
                    .background(Color.LightGray)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = "EXPIRING",
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }

        LabelBadge.NONE -> {}
    }
}

@Composable
fun EpisodeShelfItem(
    modifier: Modifier,
    episode: Episode
) {
    Column(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .weight(1.0F, true)
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(episode.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = ColorPainter(Color.LightGray),
                contentDescription = "contentDescr",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(280.dp)
                    .height(180.dp),
            )
            LabelBadgeOverlay(
                modifier = Modifier.align(Alignment.TopStart),
                labelBadge = episode.labelBadge
            )
        }

        Text(
            style = MaterialTheme.typography.titleMedium,
            text = episode.title,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            style = MaterialTheme.typography.titleSmall,
            text = episode.subtitle,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun PreviewEpisodeItem() {
    EpisodeShelfItem(
        modifier = Modifier,
        episode = Episode(
            title = "Episode Title",
            imageUrl = "nbc.com",
            subtitle = "episode subtitle",
            labelBadge = LabelBadge.NONE
        )
    )
}

@Preview
@Composable
private fun PreviewEpisodeItemWithBadge() {
    EpisodeShelfItem(
        modifier = Modifier,
        episode = Episode(
            title = "Episode Title",
            imageUrl = "nbc.com",
            subtitle = "episode subtitle",
            labelBadge = LabelBadge.NEW
        )
    )
}

@Composable
fun LiveShelfItem(
    modifier: Modifier,
    live: Live
) {

    Column(
        modifier = modifier
            .width(280.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(1.0F, true)
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(live.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = ColorPainter(Color.LightGray),
                contentDescription = "contentDescr",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(180.dp),
            )
        }

        Text(
            style = MaterialTheme.typography.titleMedium,
            text = live.title,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            style = MaterialTheme.typography.titleSmall,
            text = live.subtitle,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        if (!live.tagline.isNullOrEmpty()) {
            Text(
                style = MaterialTheme.typography.titleSmall,
                text = live.tagline,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLiveItem() {
    LiveShelfItem(
        modifier = Modifier,
        live = Live(
            title = "Live Title",
            imageUrl = "nbc.com",
            subtitle = "live subtitle",
            tagline = "show tagline"
        )
    )
}

@Composable
fun ShowShelfItem(
    modifier: Modifier,
    show: Show
) {
    Column(
        modifier = modifier.width(160.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1.0F, true)
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(show.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = ColorPainter(Color.LightGray),
                contentDescription = "contentDescr",
                contentScale = ContentScale.FillBounds,
            )
        }

        Text(
            style = MaterialTheme.typography.titleMedium,
            text = show.title,
            textAlign = TextAlign.Center,
            color = Color.White
        )

    }
}

@Preview
@Composable
private fun PreviewShowItem() {
    ShowShelfItem(
        modifier = Modifier,
        show = Show(
            title = "Show Title",
            imageUrl = "nbc.com",
        )
    )
}
