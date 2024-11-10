import java.io.File
import kotlin.system.exitProcess

fun main() {
    menu()
}
fun menu() {
    println("1. Jugar")
    println("2. Ver traza de último intento")
    println("3. Salir")
    print("opción: ")

    var opcion = readln().toInt()

    while (opcion>1 || opcion<3){
        when (opcion) {
            1 -> jugar()
            2 -> traza()
            3 -> {
                println("Gracias por jugar")
                exitProcess(0)
            }
            else -> {
                println("ERROR: vuelve a introducir una opción: ")
                println("1. Jugar")
                println("2. Ver traza de último intento")
                println("3. Salir")
                print("opción: ")

                opcion = readln().toInt()
            }
        }
    }

}
fun traza() {
    val traza = File("traza.txt").readText()
    println(traza)
    main()
}
fun numsecreto():String {
    val strOriginal = "123456"
    val strShuffled = strOriginal.toList().shuffled()

    var numSecreto = ""
    for (i in 0 until 4){
        numSecreto += strShuffled[i].toString()
    }
    return numSecreto
}
fun jugar(){
    val secreto = numsecreto()
    var ultTraza = "Numero secreto: $secreto\n"
    File("traza.txt").writeText(ultTraza)
    var intento = 1

    println("Teclea un número de 4 cifras sin números repetidos: ")
    var jugador = readln()

    var aciertos = 0
    var coincidencias = 0

    while (intento <= 10) {

        for (k in 0 until jugador.length){
                when {
                    jugador[k] == secreto[k] -> aciertos++
                    jugador[k] in secreto -> coincidencias++
                    else -> continue
                }
        }
        if (jugador==secreto) {
            break
        }
        println("$jugador      \u001B[42m $aciertos \u001B[43m $coincidencias \u001B[0m")

        ultTraza = "Intento $intento: $jugador, Aciertos: $aciertos, Coincidencias: $coincidencias\n"
        File("traza.txt").appendText(ultTraza)

        println("Teclea un número de 4 cifras sin números repetidos: ")
        jugador = readln()
        aciertos = 0
        coincidencias = 0

        intento++
    }
    if (jugador==secreto){
        println("FELICIDADES, has ganado!!!")
    }else{
        println("Lo siento, no adivinaste el número secreto $secreto en 10 intentos")
    }
    ultTraza = "\n"
    File("traza.txt").appendText(ultTraza)
    main()
}