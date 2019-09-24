import expression.VariableExpression
import statement.AssignStatement

/**
 * Analizator sprawdza czy wszystkie używane zmienne
 * zostały wcześniej zadeklarowane.
 *
 * @throws [CompilationError] gdy analiza się nie powiedzie.
 */
class StaticAnalyzer: Visitor() {
    private val symbolTable = HashSet<String>()

    override fun postVisit(a: AssignStatement) {
        symbolTable.add(a.id)
    }

    override fun visit(v: VariableExpression) {
        if(!symbolTable.contains(v.name))
            throw throw CompilationError("Error", "Undeclared variable: '${v.name}'", v.left, v.right)
    }
}