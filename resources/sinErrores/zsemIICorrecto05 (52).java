//[SinErrores]

class Utilidades {
    static int doble(int x) {
        return x * 2;
    }
    
    static boolean esPar(int n) {
        return n % 2 == 0;
    }
}

class TestMetodosEstaticos {
    void metodo() {
        var x = Utilidades.doble(5);
        var y = Utilidades.esPar(10);
        Object.debugPrint(42);
    }
}