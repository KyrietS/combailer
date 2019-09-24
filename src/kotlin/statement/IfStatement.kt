package statement

import Visitor
import relation.BinaryRelation

class IfStatement(val cond: BinaryRelation, val statements: List<Statement>): Statement() {
    override fun accept(v: Visitor) {
        if(!v.preVisit(this)) return
        cond.accept(v)
        statements.forEach { it.accept(v) }
        v.postVisit(this)
    }

    override fun toString(): String {
        val sb = StringBuilder("if $cond then").append('\n')
        statements.forEach {
            val str = "$it".replace("\n", "\n    ")
            sb.append("    $str\n")
        }
        sb.append("endif")
        return sb.toString()
    }
}