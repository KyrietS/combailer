package expression

import sym.MINUS

class SubExpression(e1: Expression, e2: Expression)
    : BinaryOperation(e1, MINUS, e2 ) {

    override fun toString(): String {
        return "$e1 - $e2"
    }
}