//[SinErrores]
class Calculadora {
    int sumar(int a, int b) {
        return a + b;
    }

    boolean esPar(int n) {
        return (n % 2) == 0;
    }
}

class InicializacionAtributoConMetodoCorrecta {
    Calculadora calc = new Calculadora();
    int suma = calc.sumar(5, 3);
    boolean par = calc.esPar(10);

    void main() {

    }
}