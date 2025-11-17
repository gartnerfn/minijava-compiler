//[SinErrores]

class Figura {
    int area() {
        return 0;
    }
}

class Rectangulo extends Figura {
    int base;
    int altura;
    
    int area() {
        return base * altura;
    }
}

class TestPolimorfismo {
    void metodo() {
        var fig = new Rectangulo();
        var a = fig.area();
        
        var figura = new Figura();
        figura = new Rectangulo();
        var area = figura.area();
    }
}
