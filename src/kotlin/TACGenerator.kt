import expression.*
import relation.BinaryRelation
import statement.*
import java.util.*

/**
 * Generator kodu trójadresowego (Three-address Code)
 */
class TACGenerator: Visitor() {
    private var counter = 1
    private val stack = Stack<String>()

    private var jumpCounter = 1

    override fun preVisit(a: AssignStatement): Boolean {
        printComment("$a")
        return true
    }
    override fun postVisit(a: AssignStatement){
        val temp = stack.pop()
        printLine("${a.id} = $temp")
    }
    override fun preVisit(p: PrintStatement): Boolean {
        printComment("$p")
        return true
    }
    override fun postVisit(p: PrintStatement) {
        val temp = stack.pop()
        printLine("PRINT $temp")
    }

    override fun preVisit(b: BinaryOperation): Boolean {
        val op = when(b.op) {
            sym.PLUS -> "+"
            sym.MINUS -> "-"
            sym.MULTIPLY -> "*"
            else -> throw CompilationError("Error", "Unknown binary operation code [${b.op}].")
        }

        if(stack.isEmpty())                         // Lewe wyrażenie nie zostało jeszcze odwiedzone
            b.e1.accept(this)
        val temp1 = stack.pop()                     // Wartość lewego wyrażenia
        b.e2.accept(this)
        val temp2 = stack.pop()                     // Wartość prawego wyrażenia

        printLine("t$counter = $temp1 $op $temp2")
        stack.push("t$counter")                // Wartość wyrażenia po wykonaniu działania
        counter++

        return false
    }

    override fun preVisit(i: IfStatement): Boolean {
        printComment("if ${i.cond} then")

        i.cond.accept(this)
        val e2 = stack.pop()!!
        val e1 = stack.pop()!!

        val relSign = when(i.cond.relation) {
            sym.GT -> "<="
            sym.LT -> ">="
            sym.EQ -> "!="
            else ->
                throw CompilationError("Error", "Unknown binary relation code [${i.cond.relation}]")
        }

        val jumpLabel = "S$jumpCounter"
        jumpCounter++
        printLine("if $e1 $relSign $e2 goto $jumpLabel")
        i.statements.forEach { it.accept(this) }
        printLabel(jumpLabel)

        return false
    }

    override fun preVisit(b: BinaryRelation): Boolean {
        b.e1.accept(this)
        val e1 = stack.pop()
        b.e2.accept(this)
        val e2 = stack.pop()

        stack.push(e1)
        stack.push(e2)
        return false
    }

    override fun visit(v: VariableExpression) {
        stack.push(v.name)
    }

    override fun visit(n: NumberExpression) {
        stack.push("$n")
    }

    private fun printLine(line: String) {
        println("\t$line")
    }
    private fun printLabel(label: String) {
        print("$label:\n")
    }
    private fun printComment(comment: String) {
        println(" ".repeat(40) + "; $comment")
        val cursorUp  = "\u001B[1A"
        print(cursorUp)
    }
}