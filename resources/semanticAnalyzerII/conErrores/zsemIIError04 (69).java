//[Error:+|5]

class ErrorSumaTipos {
    void metodo() {
        var x = 5 + true; // ERROR: + solo acepta int
    }
}