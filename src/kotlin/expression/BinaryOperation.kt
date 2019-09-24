package expression

import Visitor

abstract class BinaryOperation(val e1: Expression, val op: Int, val e2: Expression)
    : Expression() {

    override fun accept(v: Visitor) {
        if(!v.preVisit(this)) return
        e1.accept(v)
        e2.accept(v)
        v.postVisit(this)
    }
}
