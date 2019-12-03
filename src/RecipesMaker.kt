import model.Ingredient
import model.Recipe

// Recipes Maker

fun main() {
    var recipes:MutableList<Recipe> = mutableListOf()

    menu@ while (true){
        var mensaje_inicio = """
            :: Bienvenido a Recipe Maker::
            
            Seleccione la opcion deseada
            1. Hacer una receta
            2. Ver mis recetas
            3. Salir
        """.trimIndent()
2
        println(mensaje_inicio)

        var response:String = readLine()?:"" //Operador elvis, "" por default

        when(response){
            "1" -> recipes.add(makeRecipe())
            "2" -> viewRecipe(recipes)
            "3" -> break@menu

            else -> println("\nRespuesta erronea, por favor vuelva a intentarlo\n\n")
        }
    }
    println("..Saliendo de RecipeMaker")
}



// ::::::::::::::::::: Ingredientes :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

fun selectIngredients():List<Ingredient>{
    var selectIngredients:MutableList<Ingredient> = mutableListOf()
    menu@while (true){
        val msjCategoria = """
               Ingresa ingredientes para tu receta:
               Selecciona una categoría
               1. Agua
               2. Lácteos
               3. Carnes, Legumbres y huevos
               4. Verduras
               5. Frutas
               6. Granos
               7. Aceites
              
                 ...pulsa enter para salir
             """.trimIndent()
        println(msjCategoria)

        val response:String = readLine()?:""
        if (response.isEmpty()){break@menu}

        try {
            val category = response.toInt()
            when(category){
                in 1..7 -> selectIngredients.add(getIngredient(category))
                else -> println("\nRespuesta erronea, vuelve a intentarlo\n\n")
            }
        }catch (e: Exception){
            println("\nIngrese un numero del 1 al 7 por favor, vuelve a intentarlo.\n\n")
        }

    }
    return selectIngredients
}

fun getIngredient(category:Int): Ingredient {
    var ingredient:String=openList(getCategory(category))

    println("Escriba la unidad de medida: ")
    val unidad:String = readLine()?:""

    while (true){
        println("Escriba la cantidad como número: ")
        val cantidad= readLine()?:""

        try {
            val cant = cantidad.toDouble()
            return Ingredient(ingredient,cant,unidad)
        }catch (e:Exception){
            println("Cantidad errónea")
        }
    }


}

fun getCategory(category:Int):List<String>{
    when(category){
        1 -> return listOf ("Agua Hirviendo","Agua Natural","Agua Hervida")  //Agua
        2 -> return listOf ("Queso Panela","Leche","Yogurth","Queso Parmesano", "Queso Gouda")//Lacteos
        3 -> return listOf ("Res","Pollo","Pescado","Jamón","Salchicha","Chorizo")  //Carnes
        4 -> return listOf ("Lechuga","Tomate","Pepino","Limón","Zanahoria","Pimiento") //Verduras
        5 -> return listOf ("Fresa","Plátano","Uvas","Manzana","Naranja","Pera","Cereza") // Frutas
        6 -> return listOf ("Avena","Trigo","Arroz","Maiz")  //Granos
        7 -> return listOf ("Aceite de Oliva","Aceite de Cocina","Chimichurri") //Aceites
        else -> return listOf("")
    }
}

fun openList(list:List<String>):String{
    while (true){
        println("Elige un ingrediente:")

        for ((index,item) in list.withIndex()){
            println("${index+1} .- $item")
        }

        val ingrediente:String = readLine()?:""

        try {
            val opc = ingrediente.toInt()
            //si esta entre 1 y el tamaño devuelve item, si no repite
            if(opc in 1..list.size){
                return list.elementAt((opc-1))
            }else{
                println("Respuesta erronea, vuelve a intentarlo\n")
            }
        }catch (e:Exception){
            println("Respuesta erronea, por favor vuelve a intentarlo\n")
        }
    }
}

// :::::::::::::::::::.: Receta :::::::::::::::::::::::::

fun makeRecipe():Recipe{
    println("Por favor ingrese el nombre de receta")
    var nombre_receta:String= readLine()?.toString()?:"Sin_nombre"
    if(nombre_receta.isEmpty()){
        nombre_receta = "Receta sin nombre"
    }

    val ingredientes = selectIngredients() // devuelve lista de ingredientes

    println("Ingresa el modo de preparación: \n")
    var instruccions: String = readLine()?:"Receta sin modo de preparacion"
    if (instruccions.isEmpty()){instruccions="Receta si modo de preparacion"}
    println("\n\n")

    return Recipe(nombre_receta,ingredientes,instruccions)
}

fun viewRecipe(recipes:List<Recipe>){
    menu@while (true){
        val title="     :: Mis recetas son:\n"
        println("title")

        if (recipes.isNotEmpty()){
            for ((index,recipe)in recipes.withIndex()){
                println("${index+1}.- ${recipe.getRecipeName()}")
            }

            println("\nIngresa el numero de receta que quieres ver, o enter para salir")
            val recipeNum:String = readLine()?:""

            if (recipeNum.equals("")){
                break@menu
            }
            else{
                try {
                    recipes.elementAt(recipeNum.toInt()-1).viewRecipe()
                    println(" ..Pulsa enter para regresar a las recetas")
                    readLine()
                }catch (e:Exception){//out of bounds or NumberFormatException
                    println("\n Respuesta erronea, vuelve a intentarlo\n\n")
                }
            }
        }
    }
}

