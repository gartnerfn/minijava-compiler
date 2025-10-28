//[SinErrores]

class Punto {
    int x;
    int y;
}
class TestParentesis {
    void metodo() {
        var x = (((42)));
        var y = ((("hola")));
        var z = (((true)));
        var obj = (((new Punto())));
    }
}