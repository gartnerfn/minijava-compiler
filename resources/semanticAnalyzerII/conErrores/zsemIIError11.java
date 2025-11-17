///[Error:=|14]

class A {
    public B c;

    private A(){

    }

    public void m2(int x){
        var a = new A();
        var b = a.c;

        a.c.metodo(3) = new B();

    }

    public int m3(){
        return 4;
    }
}

class B  {
    public int b;

    public void metodo(int x){

    }
}


class Init{
    static void main()
    {

    }


}


