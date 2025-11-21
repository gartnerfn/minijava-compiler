///5&exitosamente

class A {
    int x;

    public A(){
        x = 5;
    }

    public void m1(){
        debugPrint(x);
    }
}

class Init{
    static void main()
    {
        var a = new A();
        a.m1();
    }
}


