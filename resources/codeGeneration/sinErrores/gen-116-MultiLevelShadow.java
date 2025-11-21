///3&exitosamente
class TestShadowingCadena {
    static void main() {
        var obj = new ClaseC5();
        System.printIln(obj.x);
    }
}
class ClaseA5 {
    public int x;
    ClaseA5() {
        this.x = 1;
    }
}
class ClaseB5 extends ClaseA5 {
    public int x;
    ClaseB5() {
        this.x = 2;
    }
}
class ClaseC5 extends ClaseB5 {
    public int x;
    ClaseC5() {
        this.x = 3;
    }
}