package com.robonouveau.nbc.takehome.api

import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robonouveau.nbc.takehome.data.ScreenPageResponse
import com.robonouveau.nbc.takehome.interactors.ReadFileInteractor
import javax.inject.Inject

enum class MockHomepageVariants(internal val filename: String) {
    DEFAULT("homepage.json"),
    MORE_SHELVES("homepage_more_shelves.json")
}

class MockNbcService @Inject constructor(
    private val fileInteractor: ReadFileInteractor
) : NbcService {

    // Allowing this to be updated at runtime if we want to make a testing feature to change it at runtime
    val homepageVariant = mutableStateOf(MockHomepageVariants.MORE_SHELVES)

    override suspend fun getHomepage(): Result<ScreenPageResponse> {
        return parseScreenPageResponse(homepageVariant.value.filename)
    }

    private fun parseScreenPageResponse(filename: String): Result<ScreenPageResponse> {
        val jsonStringResult = fileInteractor.readLocalFile(filename)
        val screenResponse = object : TypeToken<ScreenPageResponse>() {}.type
        return jsonStringResult.mapCatching { Gson().fromJson(it, screenResponse) }
    }
}
