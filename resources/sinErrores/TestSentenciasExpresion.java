//[SinErrores]

class TestSentenciasExpresion {
    int campo;
    
    void metodo1() {
        var x = 1;
    }
    
    void metodo() {
        metodo1();
        campo = 10;
        var obj = new TestSentenciasExpresion();
        obj.metodo1();
        obj.campo = 20;
    }
}