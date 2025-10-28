//[Error:metodo|8]
class ErrorTipoParametro {
    void metodo(int x) {
        var y = 1;
    }
    
    void llamada() {
        metodo(true); // ERROR: boolean no conforma con int
    }
}
