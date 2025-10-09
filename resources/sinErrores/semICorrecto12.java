///[SinErrores]

abstract class A {
    boolean b = true;
    A a = new A();

    public boolean m2(){
        return true;
    }

    abstract boolean m3();
}

abstract class C extends A{
    boolean m3(){}

    void m4(){

    }
}

class B extends A {
    B m1(){}

    boolean m3(){

    }
}

