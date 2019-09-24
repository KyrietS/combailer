package expression

import Visitor

class BracesExpression(private val e: Expression): Expression() {

    override fun accept(v: Visitor) {
        if(!v.preVisit(this)) return
        e.accept(v)
        v.postVisit(this)
    }

    override fun toString(): String {
        return "($e)"
    }
}