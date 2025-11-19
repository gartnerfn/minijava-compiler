class A {
    public B c;

    public A(){

    }

    public void m1(){

    }

    public void m2(int x){
        var a = new A();
        var b = a.c;

        a.c = new B();


    }

    public A m3(){
        return new A();
    }
}

class B  {
    public int b;

    public void metodo(int x){
        var p1 = new A();
        p1.m3().m1();
    }
}


class Init{
    static void main()
    {

    }


}


