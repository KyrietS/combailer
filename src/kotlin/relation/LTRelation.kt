package relation

import sym.LT
import expression.Expression

class LTRelation(e1: Expression, e2: Expression)
    : BinaryRelation(e1, LT, e2) {
    override fun toString(): String {
        return "$e1 < $e2"
    }
}