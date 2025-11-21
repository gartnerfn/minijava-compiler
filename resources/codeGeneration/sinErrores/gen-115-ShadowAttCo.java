///111&222&exitosamente
class TestShadowingCoexistencia {
    static void main() {
        var obj = new ClaseB4();
        System.printIln(obj.obtenerValorHeredado());
        System.printIln(obj.obtenerValorPropio());
    }
}
class ClaseA4 {
    public int dato;
    ClaseA4() {
        this.dato = 111;
    }
    int obtenerValorHeredado() {
        this.dato = 111;
        return this.dato;
    }
}
class ClaseB4 extends ClaseA4 {
    public int dato;  // Sombrea
    ClaseB4() {
        this.dato = 222;
    }
    int obtenerValorPropio() {
        return this.dato;
    }
}