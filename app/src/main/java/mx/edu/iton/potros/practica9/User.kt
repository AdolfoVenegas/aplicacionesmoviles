package mx.edu.iton.potros.practica9

data class User(var firstName: String?=null,
    var lastName:String?=null,
    var age:String?=null){
    override fun toString() = firstName+"\t"+lastName+"\t"+age
}
