package com.robonouveau.nbc.takehome.data

import com.google.gson.annotations.SerializedName

data class ScreenPage (
    val page: String,
    val shelves: List<Shelf>
)

class ScreenPageResponse(
    @field:SerializedName("page") val page: String? = null,
    @field:SerializedName("shelves") val shelves: List<ShelfResponse>? = null,
)

fun ScreenPageResponse.toScreenPage(): ScreenPage {
    return ScreenPage(
        page = this.page ?: "",
        shelves = this.shelves?.mapNotNull { it.toShelf() }.orEmpty()
    )
}
