import java.util.*

class Nodo(val dato: String) {
    var siguiente: Nodo? = null
}

class ListaEnlazada {
    private var cabeza: Nodo? = null

    fun agregarAlPrincipio(dato: String) {
        val nuevoNodo = Nodo(dato)
        nuevoNodo.siguiente = cabeza
        cabeza = nuevoNodo
    }

    fun eliminar(dato: String): Boolean {
        var temp = cabeza
        var prev: Nodo? = null

        if (temp != null && temp.dato == dato) {
            cabeza = temp.siguiente
            return true
        }

        while (temp != null && temp.dato != dato) {
            prev = temp
            temp = temp.siguiente
        }

        if (temp == null) {
            return false
        }

        prev?.siguiente = temp.siguiente
        return true
    }

    fun mostrarLista() {
        var temp = cabeza
        while (temp != null) {
            println("- ${temp.dato}")
            temp = temp.siguiente
        }
    }
}

class Hotel(val nombre: String, val capacidad: Int) {
    private val listaHuespedes = ListaEnlazada()

    fun checkIn(nombre: String): Boolean {
        if (listaHuespedes.mostrarLista().javaClass < capacidad) {
            listaHuespedes.agregarAlPrincipio(nombre)
            return true
        }
        return false
    }

    fun checkOut(nombre: String): Boolean {
        return listaHuespedes.eliminar(nombre)
    }

    fun mostrarHuespedes() {
        println("Huespedes actualmente en $nombre:")
        listaHuespedes.mostrarLista()
    }
}

private operator fun <T> Class<T>.compareTo(capacidad: Int): Int {

    return TODO("Provide the return value")
}

fun main() {
    val hotel = Hotel("Ejemplo Hotel", 5)

    val scanner = Scanner(System.`in`)
    var opcion: Int

    do {
        println("1. Check-in")
        println("2. Check-out")
        println("3. Mostrar huespedes")
        println("4. Salir")
        println("Elige una opción: ")

        opcion = scanner.nextInt()

        when (opcion) {
            1 -> {
                println("Ingresa el nombre del huesped: ")
                val nombreHuesped = scanner.next()
                if (hotel.checkIn(nombreHuesped)) {
                    println("$nombreHuesped ha hecho check-in exitosamente.")
                } else {
                    println("Lo sentimos, no hay habitaciones disponibles.")
                }
            }
            2 -> {
                println("Ingresa el nombre del huesped: ")
                val nombreHuesped = scanner.next()
                if (hotel.checkOut(nombreHuesped)) {
                    println("$nombreHuesped ha hecho check-out exitosamente.")
                } else {
                    println("$nombreHuesped no se encuentra actualmente registrado en el hotel.")
                }
            }
            3 -> {
                hotel.mostrarHuespedes()
            }
            4 -> {
                println("Saliendo...")
            }
            else -> println("Opción inválida. Por favor elige de nuevo.")
        }
    } while (opcion != 4)
}
