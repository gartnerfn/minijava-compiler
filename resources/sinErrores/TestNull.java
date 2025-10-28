//[SinErrores]

class TestNull {
    Punto campo;
    
    void metodo(Punto param) {
        var local = new Punto();
        campo = null;
        param = null;
        
        var comparacion1 = local == null;
        var comparacion2 = null == campo;
        var comparacion3 = local == campo;
    }
}
class Punto {
    int x;
    int y;
}