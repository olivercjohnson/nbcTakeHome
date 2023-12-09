package com.robonouveau.nbc.takehome.data

import com.robonouveau.nbc.takehome.MockObjects
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ShelfTest {
    @Test
    fun `parses valid shelfResponse into shelf`() {
        val shelfResponse = ShelfResponse(
            title = "shelf title",
            type = "Shelf",
            items = listOf( MockObjects.episodeShelfItemOne, MockObjects.showShelfItemOne)
        )
        val shelf = shelfResponse.toShelf()
        assertTrue(shelf != null)
        assertEquals(shelfResponse.title, shelf?.title)
        assertEquals(2, shelf?.items?.size)
    }

    @Test
    fun `returns null if title is missing in shelfResponse`() {
        val shelfResponse = ShelfResponse(title = null, type = "Shelf", items = emptyList())
        assertTrue(shelfResponse.toShelf() == null)
    }

    @Test
    fun `returns null if type is not shelf`() {
        val shelfResponse = ShelfResponse(title = "title", type = "notShelf", items = emptyList())
        assertTrue(shelfResponse.toShelf() == null)
    }

    @Test
    fun `handles invalid shelfItems in responseList and returns list with just the valid items`() {
        val invalidShelfItem = ShelfItemResponse(type = "unsupported", title = "someTitle", imageUrl = "nbc.com")
        val shelfResponse = ShelfResponse(
            title = "shelf title",
            type = "Shelf",
            items = listOf( MockObjects.episodeShelfItemOne, MockObjects.showShelfItemOne, invalidShelfItem)
        )
        val shelf = shelfResponse.toShelf()
        assertTrue(shelf != null)
        assertEquals(shelfResponse.title, shelf?.title)
        assertEquals(2, shelf?.items?.size)
    }
}
