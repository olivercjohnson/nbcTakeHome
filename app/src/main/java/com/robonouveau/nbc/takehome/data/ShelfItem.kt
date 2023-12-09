package com.robonouveau.nbc.takehome.data

import com.google.gson.annotations.SerializedName

enum class LabelBadge {
    NEW,
    EXPIRING,
    NONE;

    companion object {
        fun fromResponse(response: String?): LabelBadge {
            return when (response) {
                "NEW" -> NEW
                "EXPIRING" -> EXPIRING
                else -> NONE
            }
        }
    }
}

sealed class ShelfItem(
    val title: String,
    val imageUrl: String,
)

class Episode(
    title: String,
    imageUrl: String,
    val subtitle: String,
    val labelBadge: LabelBadge
) : ShelfItem(title, imageUrl)

class Show(
    title: String,
    imageUrl: String,
) : ShelfItem(title, imageUrl)

class Live(
    title: String,
    imageUrl: String,
    val subtitle: String,
    val tagline: String?
) : ShelfItem(title, imageUrl)

data class ShelfItemResponse(
    @field:SerializedName("type") val type: String? = null,
    @field:SerializedName("tagline") val tagline: String? = null,
    @field:SerializedName("title") val title: String? = null,
    @field:SerializedName("subtitle") val subtitle: String? = null,
    @field:SerializedName("image") val imageUrl: String? = null,
    @field:SerializedName("labelBadge") val labelBadge: String? = null,
)

/**
 * Returns the valid representation of a ShelfItem for its
 * given ShelfItemResponse's type, null if unsupported type or
 * if there are invalid fields of a supported type.
 */
fun ShelfItemResponse.toShelfItem(): ShelfItem? {
    return when (this.type) {
        "Show" -> this.toShow()
        "Episode" -> this.toEpisode()
        "Live" -> this.toLive()
        else -> null
    }
}

private fun ShelfItemResponse.toEpisode(): Episode? {
    return if (this.title == null || this.imageUrl == null || this.subtitle == null) {
        null
    } else {
        Episode(
            title = this.title,
            imageUrl = this.imageUrl,
            subtitle = this.subtitle,
            labelBadge = LabelBadge.fromResponse(this.labelBadge)
        )
    }
}

private fun ShelfItemResponse.toLive(): Live? {
    return if (this.title == null || this.imageUrl == null || this.subtitle == null) {
        null
    } else {
        Live(
            title = this.title,
            imageUrl = this.imageUrl,
            subtitle = this.subtitle,
            tagline = this.tagline
        )
    }
}

private fun ShelfItemResponse.toShow(): Show? {
    return if (this.title == null || this.imageUrl == null) {
        null
    } else {
        Show(
            title = this.title,
            imageUrl = this.imageUrl,
        )
    }
}
