package expression

import Visitor

class NumberExpression(val n: Int): Expression() {

    override fun accept(v: Visitor) {
        v.visit(this)
    }

    override fun toString(): String {
        return "$n"
    }
}