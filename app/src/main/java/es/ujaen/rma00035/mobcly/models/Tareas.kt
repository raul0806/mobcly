package es.ujaen.rma00035.mobcly.models

import java.util.*


data class Tareas(
    var name: String? = "",
    var imagePath: Int = -1,
    var date: Date? = Date(System.currentTimeMillis())
)
