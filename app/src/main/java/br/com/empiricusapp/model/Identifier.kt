package br.com.empiricusapp.model


import com.google.gson.annotations.SerializedName

data class Identifier(
    @SerializedName("slug")
    val slug: String
)