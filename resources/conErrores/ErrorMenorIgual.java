//[Error:<=|4]
class ErrorMenorIgual {
    void metodo() {
        var x = "a" <= "b"; // ERROR: <= solo acepta int
    }
}
