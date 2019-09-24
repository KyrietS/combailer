import statement.*

/**
 * Klasa wypisująca wczytany kod programu z obliczonymi
 * wstępnie wartościami wyrażeń.
 *
 * Zakłada się, że każde wyrażenie w programie musi mieć
 * wartość możliwą do ustalenia w trakcie jego wczytywania.
 */
class Printer: Visitor() {
    override fun preVisit(p: Program): Boolean {
        println("------------- ${Config.filename} -------------")
        return true
    }

    override fun postVisit(p: PrintStatement) {
        println(p.toString())
    }
    override fun postVisit(a: AssignStatement) {
        println(a.toString())
    }
    override fun preVisit(i: IfStatement): Boolean {
        println(i.toString())
        return false
    }

    override fun postVisit(p: Program) {
        println("--------------" + "-".repeat(Config.filename.length) + "--------------")
    }
}