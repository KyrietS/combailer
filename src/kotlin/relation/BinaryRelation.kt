package relation

import Visitor
import expression.Expression

abstract class BinaryRelation(val e1: Expression, val relation: Int, val e2: Expression) {
    open fun accept(v: Visitor) {
        if(!v.preVisit(this)) return
        e1.accept(v)
        e2.accept(v)
        v.postVisit(this)
    }

    abstract override fun toString(): String
}