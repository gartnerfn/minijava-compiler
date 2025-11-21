///5&exitosamente

class A {
    int x;

    public A(){
        x = 5;
        m1();
    }

    public void m1(){
        debugPrint(5);
    }
}

class Init{

    static void main()
    {
        var a = new A();
    }
}


