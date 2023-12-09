package com.robonouveau.nbc.takehome.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ShelfItemTest {

    @Test
    fun `transforming ShelfItemResponse into ShelfItem appropriately parses Episode`() {

        val episodeShelfItemOne = ShelfItemResponse(
            type = "Episode",
            title = "Episode Show One",
            subtitle = "episode subtitle one",
            imageUrl = "nbc.com",
            labelBadge = "NEW"
        )
        val episode = episodeShelfItemOne.toShelfItem()
        assertTrue(episode is Episode)
        assertTrue(episode != null)
        val episodeItem = episode as Episode
        assertEquals(episodeShelfItemOne.title, episodeItem.title)
        assertEquals(episodeShelfItemOne.subtitle, episodeItem.subtitle)
        assertEquals(episodeShelfItemOne.imageUrl, episodeItem.imageUrl)
        assertEquals(LabelBadge.NEW, episodeItem.labelBadge)
    }

    @Test
    fun `transforming ShelfItemResponse with missing fields returns null since it is invalid`() {
        val episodeShelfNoTitle = ShelfItemResponse(
            type = "Episode",
            title = null,
            subtitle = "live show subtitle one",
            imageUrl = "nbc.com",
        )
        assertTrue(episodeShelfNoTitle.toShelfItem() == null)

        val episodeShelfNoImageUrl = ShelfItemResponse(
            type = "Episode",
            title = "title, no image",
            subtitle = "live show subtitle one",
            imageUrl = null,
        )
        assertTrue(episodeShelfNoImageUrl.toShelfItem() == null)
    }

    @Test
    fun `transforming ShelfItemResponse into ShelfItem appropriately parses Show`() {
        val showShelfItemOne = ShelfItemResponse(
            type = "Show",
            title = "Show One",
            subtitle = "show subtitle one",
            imageUrl = "nbc.com",
            labelBadge = "NEW"
        )
        val show = showShelfItemOne.toShelfItem()
        assertTrue(show is Show)
        assertTrue(show != null)
        val showItem = show as Show
        assertEquals(showShelfItemOne.title, showItem.title)
        assertEquals(showShelfItemOne.imageUrl, showItem.imageUrl)
    }

    @Test
    fun `transforming ShelfItemResponse into ShelfItem appropriately parses LiveShow`() {
        val liveShelfItemOne = ShelfItemResponse(
            type = "Live",
            title = "Live Show One",
            subtitle = "live subtitle one",
            tagline = "live show tagline",
            imageUrl = "nbc.com",
        )
        val liveShow = liveShelfItemOne.toShelfItem()
        assertTrue(liveShow is Live)
        assertTrue(liveShow != null)
        val liveShowItem = liveShow as Live
        assertEquals(liveShelfItemOne.title, liveShowItem.title)
        assertEquals(liveShelfItemOne.subtitle, liveShowItem.subtitle)
        assertEquals(liveShelfItemOne.imageUrl, liveShowItem.imageUrl)
        assertEquals(liveShelfItemOne.tagline, liveShowItem.tagline)
    }
}
