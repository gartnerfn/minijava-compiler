//[SinErrores]

class TestThis {
    int valor;
    
    void metodo() {
        var x = this;
        this.valor = 10;
        var y = this.valor;
    }
    
    TestThis getThis() {
        return this;
    }
}