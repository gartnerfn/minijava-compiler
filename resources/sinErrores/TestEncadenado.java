//[SinErrores]

class TestEncadenado {
    void metodo() {
        var calc = new Calculadora();
        var resultado = calc.sumar(5).sumar(3).obtener();
        calc.sumar(10).sumar(20);
    }
}
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