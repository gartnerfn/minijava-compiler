//[Error:metodo|8]
class ErrorParametrosMenos {
    void metodo(int a, int b) {
        var x = 1;
    }
    
    void llamada() {
        metodo(5); // ERROR: faltan parámetros
    }
}
