///999&42&exitosamente
class TestShadowingSimple {
    static void main() {
        var objB = new ClaseB3();
        var objA = new ClaseA3();
        System.printIln(objB.valor);  // Debe ser 999 (el de ClaseB3)
        System.printIln(objA.valor);  // Debe ser 42 (el de ClaseA3)
    }
}
class ClaseA3 {
    public int valor;
    ClaseA3() {
        this.valor = 42;
    }
}
class ClaseB3 extends ClaseA3 {
    public int valor;  // Sombrea el atributo de A
    ClaseB3() {
        this.valor = 999;
    }
}