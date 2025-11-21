///from parent&exitosamente
class Test55 {
    static void main() {
        var obj = new B();
        obj.parentMethod();
    }
}
class A {
    void parentMethod() {
        System.printSln("from parent");
    }
}
class B extends A { }