//[SinErrores]

class Padre {}
class Hijo extends Padre {}

class TestIgualdadObjetos {
    void metodo() {
        var p = new Padre();
        var h = new Hijo();
        var eq1 = p == h;
        var eq2 = h == p;
        var eq3 = p == null;
        var eq4 = null == h;
    }
}