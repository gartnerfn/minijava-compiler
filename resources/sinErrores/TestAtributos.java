//[SinErrores]

class TestAtributos {
    int atributo1;
    boolean atributo2;
    
    void metodo() {
        var x = atributo1;
        var y = atributo2;
        atributo1 = 42;
        atributo2 = true;
    }
}