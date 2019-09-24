package expression

import Visitor
import java_cup.runtime.ComplexSymbolFactory

class VariableExpression(val name: String,
                         val left: ComplexSymbolFactory.Location,
                         val right: ComplexSymbolFactory.Location)
    : Expression() {

    override fun accept(v: Visitor) {
        v.visit(this)
    }

    override fun toString(): String {
        return name
    }
}