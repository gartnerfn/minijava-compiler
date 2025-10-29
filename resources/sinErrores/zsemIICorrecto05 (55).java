//[SinErrores]

class Utilidades {
    public static void doble(int x) {
        ++x;
        --x;
        cuadruple(x);
    }

    public static int cuadruple(int x) {
        return 4 * x;
    }
}

class A{

}

class TestMetodosEstaticos {
    void metodo() {
        Utilidades.doble(4);
    }
}