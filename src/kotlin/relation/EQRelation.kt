package relation

import sym.EQ
import expression.Expression

class EQRelation(e1: Expression, e2: Expression)
    : BinaryRelation(e1, EQ, e2) {
    override fun toString(): String {
        return "$e1 == $e2"
    }
}