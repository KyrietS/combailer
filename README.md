# Kompilator języka imperatywnego

Prosty kompilator, który kompiluje język z wymyśloną przeze mnie składnią do postaci kodu trójadresowego.

## Wymagania

* Kompilator Javy (`javac`)
* Kompilator Kotlina (`kotlinc`)

Generatory JFlex i CUP zostały dołączone do projektu i można je uruchomić ręcznie poprzez skrypty `jflex` oraz `cup`. Zwykle nie ma takiej potrzeby, ponieważ są one automatycznie uruchamiane przez polecenia budujące projekt w `Makefile`.

## Makefile
`make build` - zbudowanie całego projektu.

`make jflex` - wygenerowanie lexera

`make cup` - wygenerowanie parsera

`make kotlin` - skompilowanie źródeł Kotlina

`make java` - skompilowanie źródeł Javy

`make run` - uruchomienie programu (musi być wcześniej skompilowany)

`make clean` - usunięcie wszystkich skompilowanych oraz wygenerowanych źródeł. (Wygenerowany kod Javy również zostanie usunięty)

## Uruchomienie pod Linuxem

```
make build
./run.sh test.in
```

## Uruchomienie pod Windowsem
TBD

## Funkcjonalności programu

* Interpretacja tokenów i przekazywanie ich do parsera wraz z informacją o położeniu w pliku źródłowym.
* Budowanie struktury AST (Abstract Syntax Tree).
* Wyświetlanie czytelnych błędów składniowych i leksykalnych.
* Zaimplementowany wzorzec Visitor zapewnia możliwość łatwego konstruowania algorytmów analizujących strukturę AST.
* Zaimplementowano algorytm analizy statycznej sprawdzający, czy użyta zmienna została wcześniej zdefiniowana.
* Zaimplementowano algorytm wypisujący ze struktury AST wczytany kod źródłowy.
* Zaimplementowano algorytm generujący kod trójadresowy ze struktury AST.

## Katalog `lib`

W katalogu `lib` znajdują się biblioteki **niezbędne do działania** CUP'a oraz Kotlin'a. Zwróć uwagę, że biblioteki te nie są potrzebne do skompilowania projektu.

## Katalog `build`

W katalogu `build` znajdują się biblioteki **niezbędne do zbudowania projektu**. Dokładniej mówiąc, umieszczone tam zostały skompilowane wersje JAR programów JFlex oraz CUP.
