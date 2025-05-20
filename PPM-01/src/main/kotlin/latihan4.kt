fun main() {
    val nilai = 85

    // Menggunakan if-else
    if (nilai >= 90) {
        println("Grade: A")
    } else if (nilai >= 80) {
        println("Grade: B")
    } else {
        println("Grade: C")
    }

    // Menggunakan when (mirip switch-case)
    val grade = when {
        nilai >= 90 -> "A"
        nilai >= 80 -> "B"
        else -> "C"
    }

    println("Hasil dengan when: Grade $grade")
}
