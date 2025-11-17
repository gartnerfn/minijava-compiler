//[SinErrores]

class Base {
    int valorBase;
}

class Derivada extends Base {
    int valorDerivada;
    
    void metodo() {
        var x = valorBase;
        var y = valorDerivada;
        valorBase = 10;
        valorDerivada = 20;
    }
}