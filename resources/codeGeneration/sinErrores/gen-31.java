///exitosamente

class A {
    public void m1(){
        m2();
    }

    public void m2(){
        debugPrint(15);
    }
}

class Init{
    static void main()
    {
        var a = new A();
        a.m1();
    }
}


