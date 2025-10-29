//[SinErrores]

class Utilidades {
    public A doble(int x) {
        return (new A());
    }

    public void m2(){

    }
}

class A{
    Utilidades g;

    public void m1(){
        var u = new Utilidades();

        u.doble(2).g.m2();
    }
}

class TestMetodosEstaticos {
    void metodo() {

    }
}