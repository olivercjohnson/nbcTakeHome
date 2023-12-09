package com.robonouveau.nbc.takehome.data

import com.robonouveau.nbc.takehome.MockObjects
import com.robonouveau.nbc.takehome.api.NbcService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ScreenRepositoryTest {

    lateinit var fakeService: NbcService
    lateinit var sut: ScreenRepository

    internal class FakeNbcService : NbcService {
        var screenPageResponse: Result<ScreenPageResponse> = Result.failure(UnknownError())
        override suspend fun getHomepage(): Result<ScreenPageResponse> {
            return screenPageResponse
        }
    }

    @Before
    fun setup() {
        fakeService = FakeNbcService()
        sut = ScreenRepository(fakeService)
    }

    @Test
    fun `GetHomepage returns valid homepage if Service returns successful response`() = runTest {
        val successfulResponse = ScreenPageResponse(
            page = "HOMEPAGE",
            shelves = listOf(
                MockObjects.shelfOne
            )
        )
        (fakeService as FakeNbcService).screenPageResponse = Result.success(successfulResponse)

        val homepage = sut.getHomepage()

        assertTrue(homepage.isSuccess)
        val homepageResult = homepage.getOrNull()
        assertNotNull(homepageResult)
        assertEquals(1, homepageResult?.shelves?.size)
        val firstShelf = homepageResult?.shelves?.get(0)
        assertEquals(MockObjects.shelfOne.title, firstShelf?.title)
        assertEquals(MockObjects.shelfOne.items?.size, firstShelf?.items?.size)
    }

    @Test
    fun `GetHomepage returns failure result if api service sends failure`() = runTest {
        val expectedException = IllegalStateException()
        (fakeService as FakeNbcService).screenPageResponse = Result.failure(expectedException)

        val homepage = sut.getHomepage()

        assertTrue(homepage.isFailure)
        assertEquals(expectedException, homepage.exceptionOrNull())
    }
}
