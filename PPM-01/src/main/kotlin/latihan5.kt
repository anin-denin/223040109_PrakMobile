fun main() {
    // Looping dengan for
    for (i in 1..5) {
        println("Perulangan ke-$i")
    }

    // Looping dengan while
    var j = 1
    while (j <= 5) {
        println("While loop ke-$j")
        j++
    }

    // Looping dengan do-while
    var k = 1
    do {
        println("Do-while loop ke-$k")
        k++
    } while (k <= 5)
}
