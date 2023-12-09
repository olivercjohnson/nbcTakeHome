package com.robonouveau.nbc.takehome.data

import com.google.gson.annotations.SerializedName

data class Shelf(
    val title: String,
    val items: List<ShelfItem>
)

data class ShelfResponse(
    @field:SerializedName("title") val title: String? = null,
    @field:SerializedName("type") val type: String? = null,
    @field:SerializedName("items") val items: List<ShelfItemResponse>? = null,
)

fun ShelfResponse.toShelf(): Shelf? {
    return if (type != "Shelf" || title == null) {
        null
    } else {
        Shelf(
            title = this.title,
            items = this.items?.mapNotNull { it.toShelfItem() }.orEmpty()
        )
    }
}
