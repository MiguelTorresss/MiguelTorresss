import kotlin.math.roundToInt

// Definir las dimensiones del array
val años = 3
val regiones = 4
val categorias = 5

// Crear el array tridimensional
val ventas = Array(años) {Array(regiones) {Array(categorias) {0.0}}}

// Función para ingresar los datos de ventas
fun ingresarVentas() {
    println("Ingresa las ventas por año, región y categoría:")
    for (i in 0 until años) {
        println("Año $i:")
        for (j in 0 until regiones) {
            println("  Región $j:")
            for (k in 0 until categorias) {
                print("    Categoría $k: ")
                ventas[i][j][k] = readLine()!!.toDouble()
            }
        }
    }
}

// Función para mostrar las ventas de un año
fun ventasAño(año: Int) {
    var total = 0.0
    println("Ventas del año $año:")
    for (i in 0 until regiones) {
        for (j in 0 until categorias) {
            total += ventas[año][i][j]
            println("  Región $i, Categoría $j: ${ventas[año][i][j]}")
        }
    }
    println("Total ventas año $año: $total")
}

// Función para calcular crecimiento en una región
fun crecimientoRegion(region: Int) {
    var anterior = ventas[0][region].sum()
    println("Crecimiento ventas en la región $region:")
    for (i in 1 until años) {
        var actual = ventas[i][region].sum()
        var crecimiento = ((actual - anterior) / anterior) * 100
        println(" Año $i: $crecimiento%")
        anterior = actual
    }
}

// Función para identificar categoría más vendida
fun categoriaPopular(año: Int, region: Int) {
    var maxVentas = 0.0
    var categoria = 0
    for (i in 0 until categorias) {
        if (ventas[año][region][i] > maxVentas) {
            maxVentas = ventas[año][region][i]
            categoria = i
        }
    }

    println("Categoría más vendida en la región $region en el año $año: Categoría $categoria")
}

fun main() {
    ingresarVentas()

    println()
    ventasAño(1)

    println()
    crecimientoRegion(2)

    println()
    categoriaPopular(2, 1)
}