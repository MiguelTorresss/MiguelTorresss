import java.util.*

data class Cliente(
    val nombre: String,
    val tipo: TipoCliente,
    val turno: Int,
    var fechaLlegada: String = "",
    var fechaSalida: String = ""
)

enum class TipoCliente { ESTANDAR, VIP, ADULTO_MAYOR }

data class Habitacion(val numero: Int, var ocupada: Boolean = false, val costo: Int)

class Piso(val numero: Int, val habitaciones: List<Habitacion>)

val queue = PriorityQueue<Cliente>(compareBy({ it.turno }, { it.tipo.ordinal }))

val pisos = mutableListOf<Piso>()

// Función para asignar habitaciones
fun asignarHabitacion(cliente: Cliente, fechaLlegada: String, fechaSalida: String, costo: Int) {
    var habitacionDisponible: Habitacion? = null
    if (cliente.nombre == "ClienteEspecial") {
        val penthouse = pisos.first { it.numero == 6 }
        habitacionDisponible = penthouse.habitaciones.find { !it.ocupada }
        if (habitacionDisponible != null) {
            habitacionDisponible.ocupada = true
            cliente.fechaLlegada = fechaLlegada
            cliente.fechaSalida = fechaSalida
            println(
                "Se ha asignado el penthouse al cliente especial ${cliente.nombre} desde ${cliente.fechaLlegada} hasta ${cliente.fechaSalida} con un costo de $costo dólares por noche"
            )
            println("Saludo al huésped: Buenos días, ${cliente.nombre}. ¿En qué más puedo ayudarte?")
        } else {
            println("Lo sentimos, el penthouse está ocupado para ${cliente.nombre}")
            println("Saludo al huésped: Buenos días, ${cliente.nombre}. Lamentablemente el penthouse está ocupado. ¿Hay algo más en lo que pueda ayudarte?")
        }
    } else {
        for (piso in pisos) {
            habitacionDisponible = piso.habitaciones.find { !it.ocupada }
            if (habitacionDisponible != null) break
        }
        if (habitacionDisponible != null) {
            habitacionDisponible.ocupada = true
            cliente.fechaLlegada = fechaLlegada
            cliente.fechaSalida = fechaSalida
            println(
                "Se ha asignado la habitación ${habitacionDisponible.numero} del piso ${habitacionDisponible.numero / 5 + 1} a ${cliente.nombre} desde ${cliente.fechaLlegada} hasta ${cliente.fechaSalida} con un costo de ${habitacionDisponible.costo} dólares por noche"
            )
            println("Saludo al huésped: Buenos días, ${cliente.nombre}. ¿En qué más puedo ayudarte?")
        } else {
            println("Lo sentimos, no hay habitaciones disponibles para ${cliente.nombre}")
            println("Saludo al huésped: Buenos días, ${cliente.nombre}. Lamentablemente no tenemos habitaciones disponibles en este momento. ¿Hay algo más en lo que pueda ayudarte?")
        }
    }
    println("Conversación con el huésped: ¿Desea saber qué otras facilidades ofrecemos? Gracias por su comprensión, ${cliente.nombre}.")
}

// Función para procesar el pago con diferentes métodos
fun procesarPago(cliente: Cliente, costoTotal: Int, metodoPago: String) {
    println("Estimado ${cliente.nombre}, su costo total es de $costoTotal dólares.")

    when (metodoPago.lowercase(Locale.getDefault())) {
        "efectivo" -> {
            println("Por favor, pague $costoTotal dólares en efectivo en la recepción.")
            println("Se le ha enviado un recibo a su correo electrónico. ¡Disfrute de su estancia en nuestro hotel!")
        }
        "tarjeta" -> {
            println("Se procederá a realizar el pago de $costoTotal dólares en su tarjeta de crédito registrada.")
            // Simulación de proceso de pago
            val random = Random()
            val exitoPago = random.nextBoolean()

            if (exitoPago) {
                println("El pago de $costoTotal dólares ha sido realizado con éxito.")
                println("Se le ha enviado un recibo a su correo electrónico. ¡Disfrute de su estancia en nuestro hotel!")
            } else {
                println("Lo sentimos, ha ocurrido un error al procesar su pago. Por favor, inténtelo de nuevo más tarde.")
            }
        }
        else -> {
            println("Opción de pago no válida. Por favor, elija entre 'Efectivo' o 'Tarjeta'.")
        }
    }
}

fun main() {
    val random = Random()

    val clientes = listOf(
        Cliente("Cliente1", TipoCliente.ADULTO_MAYOR, random.nextInt(100)),
        Cliente("Cliente2", TipoCliente.VIP, random.nextInt(100)),
        Cliente("Cliente3", TipoCliente.ESTANDAR, random.nextInt(100)),
        Cliente("Cliente4", TipoCliente.VIP, random.nextInt(100)),
        Cliente("Cliente5", TipoCliente.ADULTO_MAYOR, random.nextInt(100)),
        Cliente("ClienteEspecial", TipoCliente.ESTANDAR, random.nextInt(100))
    )

    val fechasLlegada = listOf("2023-10-24", "2023-10-25", "2023-10-26", "2023-10-27", "2023-10-28", "2023-10-29")
    val fechasSalida = listOf("2023-10-26", "2023-10-27", "2023-10-28", "2023-10-29", "2023-10-30", "2023-11-01")

    for (i in 0..<5) {
        val habitaciones = List(5) { j ->
            val costoBase = 20
            val tipoCliente = when (j % 3) {
                0 -> TipoCliente.ESTANDAR
                1 -> TipoCliente.VIP
                else -> TipoCliente.ADULTO_MAYOR
            }
            val costo = when (tipoCliente) {
                TipoCliente.ESTANDAR -> costoBase
                TipoCliente.VIP -> costoBase - 5
                TipoCliente.ADULTO_MAYOR -> costoBase - 10
            }
            Habitacion(i * 5 + j + 1, false, costo)
        }
        pisos.add(Piso(i + 1, habitaciones))
    }
    val penthouse = Piso(6, List(1) {
        val costoBase = 50
        val tipoCliente = TipoCliente.ESTANDAR
        val costo = when (tipoCliente) {
            TipoCliente.ESTANDAR -> costoBase
            TipoCliente.VIP -> costoBase - 5
            TipoCliente.ADULTO_MAYOR -> costoBase - 10
        }
        Habitacion(26, false, costo)
    })
    pisos.add(penthouse)

    queue.addAll(clientes)

    // Procesamiento de pago para cada cliente al final
    for (i in clientes.indices) {
        val cliente = queue.poll()
        val costo = if (cliente.nombre == "ClienteEspecial") {
            when (cliente.tipo) {
                TipoCliente.ESTANDAR -> 50
                TipoCliente.VIP -> 45
                TipoCliente.ADULTO_MAYOR -> 40
            }
        } else {
            cliente.turno
        }

        // Simulación de selección de método de pago
        val metodoPago = if (cliente.nombre == "ClienteEspecial") "tarjeta" else if (i % 2 == 0) "efectivo" else "tarjeta"

        asignarHabitacion(cliente, fechasLlegada[i], fechasSalida[i], costo)
        procesarPago(cliente, costo, metodoPago)

        println("Agradecimiento al huésped: Gracias por elegir nuestro hotel, ${cliente.nombre}.")
        println()
    }

    println("Estado actual de las habitaciones:")
    for (piso in pisos) {
        if (piso.numero == 6) {
            println("Penthouse:")
        } else {
            println("Piso ${piso.numero}:")
        }
        for (habitacion in piso.habitaciones) {
            if (habitacion.ocupada) {
                println("Habitación ${habitacion.numero}: Ocupada")
            } else {
                println("Habitación ${habitacion.numero}: Disponible - Costo: ${habitacion.costo} dólares por noche")
            }
        }
    }
}
