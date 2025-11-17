//[SinErrores]

class A {
    int x;

}

class B extends A{
    int b;
}

class Init{
    static void main()
    {
        var a = new A();
        var b = new B();

        var c = a.x;
        var d = b.x;
    }
}




