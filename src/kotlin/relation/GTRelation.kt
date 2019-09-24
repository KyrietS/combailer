package relation

import sym.GT
import expression.Expression

class GTRelation(e1: Expression, e2: Expression)
    : BinaryRelation(e1, GT, e2) {
    override fun toString(): String {
        return "$e1 > $e2"
    }
}