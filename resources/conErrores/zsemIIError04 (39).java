//[Error:metodo|10]
class ErrorMetodoNoStatico {
    void metodo() {
        var x = 1;
    }
}

class ErrorLlamadaNoStatica {
    void llamada() {
        ErrorMetodoNoStatico.metodo(); // ERROR: metodo no es static
    }
}
