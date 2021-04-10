package br.com.empiricusapp.model


import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photoLargeUrl")
    val photoLargeUrl: String,
    @SerializedName("photoSmallUrl")
    val photoSmallUrl: String
)