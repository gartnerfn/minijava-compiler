//[Error:metodo|8]
class ErrorLlamadaVoid {
    void metodoVoid() {
        var x = 1;
    }
    
    void metodo() {
        metodoVoid().metodo(); // ERROR: void no tiene métodos
    }
}
