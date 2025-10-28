//[Error:=|16]

class A {

}

class B extends A{

}

class C {
    A a = null;
    B b = null;

    void m1() {
        b = a;
    }
}


class Init{
    static void main()
    { }
}





