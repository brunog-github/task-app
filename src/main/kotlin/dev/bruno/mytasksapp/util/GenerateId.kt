package dev.bruno.mytasksapp.util

import java.util.*

object GenerateId {
    fun generate(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }
}