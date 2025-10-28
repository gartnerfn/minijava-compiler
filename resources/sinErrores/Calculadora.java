//[SinErrores]

class Calculadora {
    int valor;
    
    Calculadora sumar(int x) {
        valor = valor + x;
        return this;
    }
    
    int obtener() {
        return valor;
    }
}