package statement

import Visitor

abstract class Statement {
    abstract fun accept(v: Visitor)
    abstract override fun toString(): String
}