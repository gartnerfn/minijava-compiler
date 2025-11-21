///10&20&exitosamente
class TestHerenciaSimple {
    static void main() {
        var obj = new ClaseB1();
        System.printIln(obj.atributoA);
        System.printIln(obj.atributoB);
    }
}
class ClaseA1 {
    public int atributoA;
    ClaseA1() {
        this.atributoA = 10;
    }
}
class ClaseB1 extends ClaseA1 {
    public int atributoB;
    ClaseB1() {
        this.atributoA = 10;
        this.atributoB = 20;
    }
}