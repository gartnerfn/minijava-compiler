///[Error:x|6]

class A {
    void m1(int p1)
    {
        var b = C.m2().x;
    }
}
class C{
    static A m2(){ return new A();}
}



