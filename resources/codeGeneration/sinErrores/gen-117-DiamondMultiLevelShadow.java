///20&100&10&200&exitosamente
class TestHerenciaDiamante {
    static void main() {
        var objB = new ClaseB6();
        var objC = new ClaseC6();
        System.printIln(objB.getComun());
        System.printIln(objB.especificoB);
        System.printIln(objC.getComun());
        System.printIln(objC.especificoC);
    }
}
class ClaseA6 {
    public int comun;
    ClaseA6() {
        this.comun = 10;
    }
    public int getComun() {
        this.comun = 10;
        return this.comun;
    }
}
class ClaseB6 extends ClaseA6 {
    public int especificoB;
    ClaseB6() {
        this.especificoB = 100;
    }
    public int getComun() {
        this.comun = 20;
        return this.comun;
    }
}
class ClaseC6 extends ClaseA6 {
    public int especificoC;
    ClaseC6() {
        this.especificoC = 200;
    }
}