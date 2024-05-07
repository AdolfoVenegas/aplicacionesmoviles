package mx.edu.iton.potros.practica6

data class Pelicula(
    var titulo:String,
    var image:Int,
    var header: Int,
    var sinopsis: String,
    var seats:ArrayList<Cliente>)
