package com.robonouveau.nbc.takehome

import com.robonouveau.nbc.takehome.data.ShelfItemResponse
import com.robonouveau.nbc.takehome.data.ShelfResponse

object MockObjects {
    val liveShelfItemOne = ShelfItemResponse(
        type = "Live",
        title = "Live Show One",
        subtitle = "live show subtitle one",
        imageUrl = "nbc.com"
    )
    val showShelfItemOne = ShelfItemResponse(
        type = "Show",
        title = "Show One",
        imageUrl = "nbc.com"
    )
    val episodeShelfItemOne = ShelfItemResponse(
        type = "Episode",
        title = "Live Show One",
        subtitle = "live show subtitle one",
        imageUrl = "nbc.com",
        labelBadge = "NEW"
    )

    val shelfOne = ShelfResponse(
        title = "shelfOne",
        type = "Shelf",
        items = listOf(
            showShelfItemOne,
            episodeShelfItemOne
        )
    )

}