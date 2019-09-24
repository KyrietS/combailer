import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;

%%

%class Lexer

%cup
%implements sym
%char
%line
%column

// Funkcje pomocnicze (tworzenie symboli z informacją o pozycji)
%{
    private ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();

    private Symbol symbol(String name, int id) {
        return symbol(name, id, null);
    }
    private Symbol symbol(String name, int id, Object val) {
        Location left = new Location(yyline+1, yycolumn+1, yychar+1);
        Location right = new Location(yyline+1, yycolumn+yylength(), yychar+yylength());
        Symbol complexSymbol = symbolFactory.newSymbol(name, id, left, right, val);
        return (ComplexSymbolFactory.ComplexSymbol)complexSymbol;
    }

    private void compilationError(String msg) {
        Location left = new Location(yyline+1, yycolumn+1, yychar+1);
        Location right = new Location(yyline+1, yycolumn+yylength(), yychar+yylength());
        throw new CompilationError("Error", msg, left, right);
    }
%}

// Symbol zwracany po dotarciu do końca pliku
%eofval{
     return symbolFactory.newSymbol("EOF", EOF, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+1,yychar+1));
%eofval}

NEW_LINE = \n|\r\n
ID       = [a-zA-Z_][a-zA-Z0-9_]*
NUMBER   = [1-9][0-9]*|"0"

%%

/* Słowa kluczowe */
"if"                    { return symbol("if", IF); }
"then"                  { return symbol("then", THEN); }
"endif"                 { return symbol("endif", ENDIF); }
"print"                 { return symbol("print", PRINT); }

/* Liczba (stała) */
{NUMBER}                { return symbol("number", NUM, Integer.parseInt(yytext())); }

/* Nazwa (zmiennej) */
{ID}                    { return symbol("identifier", ID, yytext()); }

/* Operatory logiczne */
">"                     { return symbol(">", GT); }
"<"                     { return symbol("<", LT); }
"=="                    { return symbol("==", EQ); }

/* Operatory arytmetyczne */
"+"                     { return symbol("+", PLUS); }
"-"                     { return symbol("-", MINUS); }
"*"                     { return symbol("*", MULTIPLY); }
"("                     { return symbol("(", LPAREN); }
")"                     { return symbol(")", RPAREN); }
"="                     { return symbol("=", ASSIGN); }

";"                     { return symbol(";", END); }
{NEW_LINE}              { /* ignore */ }
[ \t]                   { /* ignore */ }
.                       { compilationError("Unexpected character '" + yytext() + "'"); }
