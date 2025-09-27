///[SinErrores]

class Ops {
    public void test() {
        var a = 3 + 5 * 2;
        if (a >= 10 && a != 12) {
            while (a < 20) { a = a + 1; }
        } else a = 0;
    }
}

class R {
    public void demo() {
        this.metodo().campo = new Obj(1,2);
        Obj.metodoEstatico( "txt" );
    }
}

class Lits {
    void show() {
        var s = "Hola \"mundo\"";
        var c = 'x';
        var n = null;
        var t = true;
    }
}