import java.io.BufferedReader
import java.io.FileReader
import kotlin.system.exitProcess
import java_cup.runtime.ComplexSymbolFactory

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Nie podano ścieżki do pliku źródłowego.")
        exitProcess(-1)
    }

    Config.filename = args[0]
    val reader = BufferedReader(FileReader(Config.filename))
    val lexer = Lexer(reader)

    val symbolFactory = ComplexSymbolFactory()
    val parser = Parser(lexer, symbolFactory)

    try {
        val program = parser.parse().value as Program
        compile(program)
    } catch (e: CompilationError) {
        reportError(e.title, e.msg, e.loc, e.loc2)
    } catch(e: Throwable) {
        reportError("Error", e.message ?: e.javaClass.canonicalName)
    } finally {
        if(Config.numOfErrors == 1) {
            println("1 error occurred.")
        } else if(Config.numOfErrors > 1) {
            println("${Config.numOfErrors} errors occurred.")
        }
    }
}

fun compile(p: Program) {
    val staticAnalyzer = StaticAnalyzer()
    p.accept(staticAnalyzer)

    val printer = Printer()
    p.accept(printer)

    val tac = TACGenerator()
    p.accept(tac)
}