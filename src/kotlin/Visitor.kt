import relation.*
import expression.*
import statement.*

@Suppress("UNUSED_PARAMETER")
abstract class Visitor {
    open fun preVisit(p: Program): Boolean { return true }
    open fun postVisit(p: Program) {}

    open fun preVisit(a: AssignStatement): Boolean { return true }
    open fun postVisit(a: AssignStatement) {}
    open fun preVisit(p: PrintStatement): Boolean { return true }
    open fun postVisit(p: PrintStatement) {}
    open fun preVisit(i: IfStatement): Boolean { return true }
    open fun postVisit(i: IfStatement) {}

    open fun preVisit(b: BinaryRelation): Boolean { return true }
    open fun postVisit(b: BinaryRelation) {}

    open fun preVisit(b: BinaryOperation): Boolean { return true }
    open fun postVisit(b: BinaryOperation) {}
    open fun preVisit(b: BracesExpression): Boolean { return true }
    open fun postVisit(b: BracesExpression) {}

    open fun visit(n: NumberExpression) {}
    open fun visit(v: VariableExpression) {}
}