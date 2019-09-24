package expression

import sym.MULTIPLY

class MulExpression(e1: Expression, e2: Expression)
    : BinaryOperation(e1, MULTIPLY, e2 ) {

    override fun toString(): String {
        return "$e1 * $e2"
    }
}