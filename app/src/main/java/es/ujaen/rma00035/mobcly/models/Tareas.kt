package es.ujaen.rma00035.mobcly.models


data class Tareas(
    var name: String? = "",
    var imagePath: String? = "",
    var day: Int,
    var month: Int,
    var year: Int,
    var hour: Int,
    var minute: Int
)
