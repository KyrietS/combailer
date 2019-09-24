package statement

import Visitor
import expression.Expression

class AssignStatement(val id: String, val ex: Expression): Statement() {

    override fun accept(v: Visitor) {
        if(!v.preVisit(this)) return
        ex.accept(v)
        v.postVisit(this)
    }

    override fun toString(): String {
        return "$id = $ex"
    }
}