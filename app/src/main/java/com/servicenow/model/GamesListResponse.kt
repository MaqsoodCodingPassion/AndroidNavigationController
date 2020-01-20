package com.servicenow.model

import com.google.gson.annotations.SerializedName

class GamesListResponse {
    @SerializedName("games")
    var games: List<GamesItem>? = null

    inner class GamesItem {
        @SerializedName("cover")
        var cover: String? = null
        @SerializedName("longDescription")
        var longDescription: String? = null
        @SerializedName("name")
        var name: String? = null
        @SerializedName("shortDescription")
        var shortDescription: String? = null

    }
}