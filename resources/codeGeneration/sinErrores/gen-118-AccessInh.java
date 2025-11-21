//50&75&exitosamente
class TestAccesoHeredadoEnMetodos {
    static void main() {
        var obj = new ClaseB7();
        obj.inicializar();
        System.printIln(obj.obtenerBase());
        System.printIln(obj.obtenerTotal());
    }
}
class ClaseA7 {
    public int base;
    int obtenerBase() {
        return this.base;
    }
}
class ClaseB7 extends ClaseA7 {
    public int adicional;
    void inicializar() {
        this.base = 50;
        this.adicional = 25;
    }
    int obtenerTotal() {
        return this.base + this.adicional;
    }
}