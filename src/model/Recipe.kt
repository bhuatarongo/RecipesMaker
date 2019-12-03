package model

//Objeto receta
class Recipe(val name:String, val ingredient:List<Ingredient>,val instructions:String){
    fun viewRecipe(){
        println("\n :: Receta: ${this.name} ::\n ")
        println("Ingredientes:\n")
        for (ingredient in this.ingredient){
            println("   ${ingredient.toString()}")
        }
        println("\nMOdo de preparacion:\n")
        println("${this.instructions}\n")
    }

    fun getRecipeName():String{
        return "${this.name}"
    }
}