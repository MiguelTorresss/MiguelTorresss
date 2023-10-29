import kotlin.random.Random

fun main() {
    val r = Random(System.currentTimeMillis())
    var d: Int
    var lista: Lista
    val k: Int
    lista = Lista() // Crea una lista vacía
    k = r.nextInt(55) // Número de nodos

    // Se insertan elementos en la lista
    repeat(k) {
        d = r.nextInt(99)
        lista = lista.insertarCabezaLista(d)
    }

    // Recorre la lista para escribir sus elementos
    println("Elementos de la lista generados al azar")
    lista.visualizar()
}

class Nodo(var dato: Int, var enlace: Nodo? = null)

class Lista(var primero: Nodo? = null) {
    fun insertarCabezaLista(entrada: Int): Lista {
        val nuevo = Nodo(entrada)
        nuevo.enlace = primero
        primero = nuevo
        return this
    }

    fun visualizar() {
        var n: Nodo? = primero
        var k = 0
        while (n != null) {
            print("${n.dato} ")
            n = n.enlace
            k++
            print(if (k % 15 != 0) " " else "\n")
        }
    }
}
