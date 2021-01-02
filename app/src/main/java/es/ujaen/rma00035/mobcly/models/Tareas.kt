package es.ujaen.rma00035.mobcly.models

import java.util.Date


data class Tareas(
    var name: String? = "",
    var imagePath: String? = "",
    var date: Date?= Date(System.currentTimeMillis())
)
