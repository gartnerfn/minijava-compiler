///B says hi&exitosamente
class Test56 {
    static void main() {
        var obj = new B();
        greetWith(obj);
    }
    static void greetWith(A a) {
        a.greet();
    }
}
class A {
    void greet() {
        System.printSln("A says hi");
    }
}
class B extends A {
    void greet() {
        System.printSln("B says hi");
    }
}