//[SinErrores]
class A {

}

class B extends A{

}

class C {
    A a = null;
    B b = null;

    void m1() {
        a = b;
    }
}


class Init{
    static void main()
    { }
}


