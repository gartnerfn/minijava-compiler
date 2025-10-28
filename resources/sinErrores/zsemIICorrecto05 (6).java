//[SinErrores]

abstract class FiguraAbstracta {
    int perimetro() {
        return 0;
    }
}

class Cuadrado extends FiguraAbstracta {
    int lado;
    
    int perimetro() {
        return lado * 4;
    }
}

class TestClaseAbstracta {
    void metodo() {
        var c = new Cuadrado();
        var p = c.perimetro();
    }
}