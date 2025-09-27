///[SinErrores]

class A {
    public A() { }
}
class B extends A {
    public B() { new A(); }
}