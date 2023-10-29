fun main() {
    // Tipos de datos primitivos
    val myInt: Int = 42
    val myDouble: Double = 3.1416
    val myChar: Char = 'A'
    val myBoolean: Boolean = true

    // Tipos de datos estructurados
    val myArray: Array<String> = arrayOf("Kotlin", "Java", "Python")
    val myList: List<Int> = listOf(1, 2, 3, 4, 5)
    val mySet: Set<Int> = setOf(1, 2, 3, 4, 5)
    val myMap: Map<Int, String> = mapOf(1 to "One", 2 to "Two", 3 to "Three")

    // Tipos de datos abstractos
    val myString: String = "Hola, Kotlin"
    val myFunction: (Int, Int) -> Int = { x, y -> x + y }

    println("Tipos de datos primitivos:")
    println("Int: $myInt")
    println("Double: $myDouble")
    println("Char: $myChar")
    println("Boolean: $myBoolean")

    println("\nTipos de datos estructurados:")
    println("Array: ${myArray.joinToString()}")
    println("List: $myList")
    println("Set: $mySet")
    println("Map: $myMap")

    println("\nTipos de datos abstractos:")
    println("String: $myString")
    println("Resultado de la función: ${myFunction(3, 5)}")
}
