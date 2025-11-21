///10&20&exitosamente
class Test54 {
    static void main() {
        var obj = new B();
        obj.a = 10;
        obj.b = 20;
        System.printIln(obj.a);
        System.printIln(obj.b);
    }
}
class A {
    public int a;
}
class B extends A {
    public int b;
}