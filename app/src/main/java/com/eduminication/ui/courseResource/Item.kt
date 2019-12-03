package com.eduminication.ui.courseResource

data class Resource(var name: String, var url: String)

data class Item(
    var name: String,
    var resources: Array<Resource>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (name != other.name) return false
        if (!resources.contentEquals(other.resources)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + resources.contentHashCode()
        return result
    }
}
