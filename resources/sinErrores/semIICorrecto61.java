//[SinErrores]

class A {
    void metodo(){

    }

    void metodo(boolean b) {

    }

    void metodo(int a, boolean b){

    }
}
class Init{
    static void main()
    {
        var a = new A();

        a.metodo();
        a.metodo(true);
        a.metodo(4, false);
    }
}


