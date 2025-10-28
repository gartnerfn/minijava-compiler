//[Error:+|8]
class ErrorAritmeticaVoid {
    void metodoVoid() {
        var x = 1;
    }
    
    void metodo() {
        var x = metodoVoid() + 5; // ERROR: void no se puede usar en aritmética
    }
}
