package com.eduminication.ui.resource

data class Item(
    val name: String,
    val courses: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (name != other.name) return false
        if (!courses.contentEquals(other.courses)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + courses.contentHashCode()
        return result
    }
}