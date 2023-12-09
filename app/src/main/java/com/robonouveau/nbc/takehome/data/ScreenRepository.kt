package com.robonouveau.nbc.takehome.data

import com.robonouveau.nbc.takehome.api.NbcService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenRepository @Inject constructor(
    private val nbcService: NbcService
) {

    suspend fun getHomepage(): Result<ScreenPage> {
        return nbcService.getHomepage().mapCatching { it.toScreenPage() }
    }

}
