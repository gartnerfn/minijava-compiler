class A {
    int a1;
    void m1(int p1)
    {
        p1 = B.m2().m2().a1;
    }
    static B m2()
    { return new B(); }
}
class B extends A{
}

class Init{
    static void main()
    {

    }


}


