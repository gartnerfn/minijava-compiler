//[Error:metodo|8]
class ErrorParametrosMas {
    void metodo(int a) {
        var x = 1;
    }
    
    void llamada() {
        metodo(5, 10); // ERROR: sobran parámetros
    }
}
