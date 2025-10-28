//[Error:metodo|8]
class ErrorParametroChar {
    void metodo(int x) {
        var y = 1;
    }
    
    void llamada() {
        metodo('a'); // ERROR: char no conforma con int
    }
}
