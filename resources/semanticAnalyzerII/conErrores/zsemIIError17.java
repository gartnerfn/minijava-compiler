///[Error:m3|6]

class A {
    void m1(int p1)
    {
        C.m2().m3();
    }
}
class C{
    static A m2(){ return new A();}
}



