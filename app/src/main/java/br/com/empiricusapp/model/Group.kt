package br.com.empiricusapp.model


import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("authors")
    val authors: List<Author>,
    @SerializedName("backgroundLarge")
    val backgroundLarge: String,
    @SerializedName("backgroundSmall")
    val backgroundSmall: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("features")
    val features: List<Feature>,
    @SerializedName("identifier")
    val identifier: Identifier,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortDescription")
    val shortDescription: String
)