//[SinErrores]

class ClaseBase {
    int obtenerValor() {
        return 42;
    }
}

class ClaseDerivada extends ClaseBase {
    void metodo() {
        var x = obtenerValor();
    }
}