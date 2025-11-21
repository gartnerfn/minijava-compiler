//100&500&exitosamente
class TestShadowingModificacion {
    static void main() {
        var obj = new ClaseB8();
        obj.modificarValores();
        System.printIln(obj.obtenerHeredado());
        System.printIln(obj.obtenerPropio());
    }
}
class ClaseA8 {
    public int valor;
    ClaseA8() {
        this.valor = 100;
    }
    int obtenerHeredado() {
        return this.valor;
    }
}
class ClaseB8 extends ClaseA8 {
    public int valor;
    ClaseB8() {
        this.valor = 200;
    }
    void modificarValores() {
        this.valor = 500;  // Solo modifica el de B
    }
    int obtenerPropio() {
        return this.valor;
    }
}