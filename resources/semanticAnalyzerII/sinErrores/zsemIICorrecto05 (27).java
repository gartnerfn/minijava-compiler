//[SinErrores]

class A {
     public B att = new B();


}
class B extends A{

}
class C {
    A a1;

    void m1(){
        a1 = new A();
        m2(a1.att);
    }

    void m2(B p1){

    }
}

class Init{
    static void main()
    { }
}


