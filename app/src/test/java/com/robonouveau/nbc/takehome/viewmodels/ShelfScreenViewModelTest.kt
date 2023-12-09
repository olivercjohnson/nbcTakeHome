package com.robonouveau.nbc.takehome.viewmodels

import com.robonouveau.nbc.takehome.data.ScreenPage
import com.robonouveau.nbc.takehome.data.ScreenRepository
import com.robonouveau.nbc.takehome.data.Shelf
import com.robonouveau.nbc.takehome.data.Show
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ShelfScreenViewModelTest {

    private lateinit var sut: ShelfScreenViewModel
    private lateinit var stubScreenRepository: ScreenRepository

    @Before
    fun setup() {
        stubScreenRepository = mockk()
        sut = ShelfScreenViewModel(stubScreenRepository)
    }

    @Test
    fun `Refreshing homepage calls to repo and sets the correct Shelves`() {
        val validScreenPage = ScreenPage(
            page = "HOMEPAGE",
            shelves = listOf(
                Shelf(
                    title = "first shelf",
                    items = listOf(
                        Show(
                            title = "show one",
                            imageUrl = "nbc.com"
                        )
                    )
                ),
                Shelf(
                    title = "second shelf",
                    items = listOf(
                        Show(
                            title = "show two",
                            imageUrl = "nbc.com"
                        ),
                        Show(
                            title = "show three",
                            imageUrl = "nbc.com"
                        )
                    )
                )
            )
        )
        coEvery { stubScreenRepository.getHomepage() } returns Result.success(validScreenPage)

        sut.refreshScreen()

        coVerify(exactly = 1) {
            stubScreenRepository.getHomepage()
        }
        assertEquals(validScreenPage.shelves, sut.shelves().value)
    }

    @Test
    fun `View model should handle refresh failure from repo`() {
        coEvery { stubScreenRepository.getHomepage() } returns Result.failure(UnknownError())

        sut.refreshScreen()

        coVerify(exactly = 1) {
            stubScreenRepository.getHomepage()
        }
        assertTrue(sut.shelves().value.isEmpty())
    }
}