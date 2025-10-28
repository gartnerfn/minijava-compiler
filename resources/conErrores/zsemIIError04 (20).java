//[Error:==|8]
class ErrorComparacionVoid {
    void metodoVoid() {
        var x = 1;
    }
    
    void metodo() {
        var x = metodoVoid() == 5; // ERROR: no se puede comparar void
    }
}
