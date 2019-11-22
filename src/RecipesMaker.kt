
// Recipes Maker
var recetas:MutableList<String> = mutableListOf<String>()
var ingredientes:MutableList<String> = mutableListOf("Agua","Leche","Carne","Verduras","Frutas","Cereal","Huevos","Aceite")



fun main() {
    exit@ while (true){
        var mensaje_inicio = """
            :: Bienvenido a Recipe Maker::
            
            Seleccione la opcion deseada
            1. Hacer una receta
            2. Ver mis recetas
            3. Salir
        """.trimIndent()

        println(mensaje_inicio)
        try {

            var response= readLine()?.toInt()?:0
            if (response==4){
                println("Por favor ingrese un dato!!")
                continue@exit
            }
                if (response==1){
                    makeRecipe()
                }
                if (response==2){
                        viewRecipe()
                }
                if (response==3){
                    break@exit
                }
        }catch (e:NumberFormatException){
                println("----> Por favor ingrese un dato valido\n")
        }

    }
}

fun makeRecipe(){
    println("Por favor ingrese el nombre de receta")
    val nombre_receta:String?= readLine()?.toString()?:"Sin_nombre"
    println("Selecciona los ingredientes:")
    for ((index,ingredient) in ingredientes.withIndex()){
        println("${index+1}.-${ingredient}")
    }
    println("\n 0.-Guardar receta\n -1.-Salir ")
    var receta_nueva:String = nombre_receta + ": "

    receta@ for ((index,product) in ingredientes.withIndex()){
        val nuevo_ingrediente: Int? = readLine()?.toInt()
        when(nuevo_ingrediente){
            in 1..(ingredientes.size-1) -> {receta_nueva=receta_nueva+ ingredientes.get(nuevo_ingrediente.toString().toInt()-1)+", "
                println(receta_nueva)
            }
            0 -> { recetas.add(receta_nueva)
                main()
            }
            !in 1..(ingredientes.size-1) -> {println("Ingrese un dato valido por favor")
                continue@receta
            }
        }
    }
}

fun viewRecipe(){
    println("\n Mis recetas son: ")
    for ((index,product) in recetas.withIndex()){
        println("${index+1}.- $product")
    }
    println("Ingrese cualquier teclado para salir")
    val texto= readLine()?:0
    main()
}