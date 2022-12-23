package com.eduardovpessoa.data.dto

import kotlinx.serialization.Serializable

typealias Companies = ArrayList<Company>

@Serializable
data class Company(
    val id: Long,
    val name: String,
    val location: Location,
    val revenue: List<Revenue>
) {
    internal fun formatAddress(): String =
        "${location.address}, ${location.city} - ${
            location.country.replace(
                "United States",
                "USA"
            )
        }"
}

@Serializable
data class Location(
    val address: String,
    val city: String,
    val country: String
)

@Serializable
data class Revenue(
    val seq: Long,
    val date: String,
    val value: Double
)
