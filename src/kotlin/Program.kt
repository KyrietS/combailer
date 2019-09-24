import statement.Statement

class Program(private val statements: List<Statement>) {

    fun accept(v: Visitor) {
        if( !v.preVisit(this) ) return
        statements.forEach { it.accept(v) }
        v.postVisit(this)
    }
}
