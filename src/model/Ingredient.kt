package model

// Clase objeto ingrediente
class Ingredient(val name:String,val quantity:Double=1.0, val meansurement_unit: String=" "){
    override fun toString(): String {
        return "${this.quantity} ${this.meansurement_unit} de ${this.name}"
    }
}