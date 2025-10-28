//[SinErrores]

class Punto {
    int x;
    int y;
}

class TestAccesoAtributos {
    void metodo() {
        var p = new Punto();
        p.x = 10;
        p.y = 20;
        var suma = p.x + p.y;
    }
}
