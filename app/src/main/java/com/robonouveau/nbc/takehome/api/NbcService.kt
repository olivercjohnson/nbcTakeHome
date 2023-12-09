package com.robonouveau.nbc.takehome.api

import com.robonouveau.nbc.takehome.data.ScreenPageResponse

interface NbcService {
    suspend fun getHomepage(): Result<ScreenPageResponse>

}
