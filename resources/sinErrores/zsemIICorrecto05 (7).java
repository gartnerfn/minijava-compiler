//[SinErrores]

class Matematica {
    int suma(int a, int b) {
        return a + b;
    }
    
    int multiplica(int a, int b) {
        return a * b;
    }
}

class TestMetodosEnExpresiones {
    void metodo() {
        var m = new Matematica();
        var resultado = m.suma(5, 3) + m.multiplica(2, 4);
        var comparacion = m.suma(1, 1) == 2;
    }
}