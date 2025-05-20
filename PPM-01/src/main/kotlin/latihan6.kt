// Fungsi biasa
fun sapa(name: String) {
    println("Halo, $name!")
}

// Lambda function
val sapaLambda: (String) -> Unit = { name ->
    println("Halo dari Lambda, $name!")
}

fun main() {
    sapa("Anin")          // Panggil fungsi biasa
    sapaLambda("Nadia")   // Panggil lambda
}
