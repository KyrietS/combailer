package expression

import sym.PLUS

class AddExpression( e1: Expression, e2: Expression)
    : BinaryOperation(e1, PLUS, e2 ) {

    override fun toString(): String {
        return "$e1 + $e2"
    }
}