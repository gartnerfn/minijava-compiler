///100&200&300&exitosamente
class TestHerenciaMultinivel {
    static void main() {
        var obj = new ClaseC2();
        System.printIln(obj.atributoA);
        System.printIln(obj.atributoB);
        System.printIln(obj.atributoC);
    }
}
class ClaseA2 {
    public int atributoA;
    ClaseA2() {
        this.atributoA = 100;
    }
}
class ClaseB2 extends ClaseA2 {
    public int atributoB;
    ClaseB2() {
        this.atributoB = 200;
    }
}
class ClaseC2 extends ClaseB2 {
    public int atributoC;
    ClaseC2() {
        this.atributoA = 100;
        this.atributoB = 200;
        this.atributoC = 300;
    }
}