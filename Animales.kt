import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val lista = ListaAnimales()
    println("¿Cuántos animales quieres agregar a la lista?")
    val numAnimales = scanner.nextInt()
    scanner.nextLine() // limpiar el buffer

    // Se agregan animales a la lista
    for (i in 0 until numAnimales) {
        println("Ingresa el nombre del animal:")
        val nombreAnimal = scanner.nextLine()
        lista.insertarCabezaLista(nombreAnimal)
    }

    // Muestra la lista de animales
    println("Lista de nombres de animales:")
    lista.visualizar()

    // Agregar nodo a la cola
    println("\nIngresa un nombre de animal para agregar al final de la lista:")
    val nombreCola = scanner.nextLine()
    lista.insertarColaLista(nombreCola)
    println("Lista de nombres de animales después de agregar al final:")
    lista.visualizar()

    // Agregar nodo entre dos nodos existentes
    println("\nIngresa el nombre del animal después del cual deseas agregar un nuevo animal:")
    val animalExistente = scanner.nextLine()
    println("Ingresa el nombre del nuevo animal:")
    val nuevoAnimal = scanner.nextLine()
    lista.insertarEntreNodos(animalExistente, nuevoAnimal)
    println("Lista de nombres de animales después de agregar entre dos nodos existentes:")
    lista.visualizar()
}

class NodoAnimales(var dato: String, var enlace: NodoAnimales? = null)

class ListaAnimales(var primero: NodoAnimales? = null) {
    fun insertarCabezaLista(entrada: String): ListaAnimales {
        val nuevo = NodoAnimales(entrada)
        nuevo.enlace = primero
        primero = nuevo
        return this
    }

    fun insertarColaLista(entrada: String) {
        val nuevo = NodoAnimales(entrada)
        if (primero == null) {
            primero = nuevo
            return
        }
        var temp = primero
        while (temp?.enlace != null) {
            temp = temp.enlace
        }
        temp?.enlace = nuevo
    }

    fun insertarEntreNodos(datoExistente: String, nuevoDato: String) {
        val nuevo = NodoAnimales(nuevoDato)
        var temp = primero
        while (temp != null) {
            if (temp.dato == datoExistente) {
                nuevo.enlace = temp.enlace
                temp.enlace = nuevo
                break
            }
            temp = temp.enlace
        }
    }

    fun visualizar() {
        var n = primero
        var k = 0
        while (n != null) {
            print("${n.dato} ")
            n = n.enlace
            k++
            print(if (k % 5 != 0) " " else "\n")
        }
    }
}
