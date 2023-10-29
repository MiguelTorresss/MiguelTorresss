import java.util.*

class Hotel(val nombre: String, val capacidad: Int) {
    var huespedes: Array<String?> = arrayOfNulls<String>(capacidad)

    fun checkIn(nombre: String): Boolean {
        for (i in huespedes.indices) {
            if (huespedes[i] == null) {
                huespedes[i] = nombre
                return true
            }
        }
        return false
    }

    fun checkOut(nombre: String): Boolean {
        for (i in huespedes.indices) {
            if (huespedes[i] == nombre) {
                huespedes[i] = null
                return true
            }
        }
        return false
    }

    fun mostrarHuespedes() {
        println("Huespedes actualmente en $nombre:")
        for (i in huespedes.indices) {
            if (huespedes[i] != null) {
                println("- ${huespedes[i]}")
            }
        }
    }
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
