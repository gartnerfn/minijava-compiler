///factory created&exitosamente
class Test59 {
    static void main() {
        var obj = A.create();
        System.printSln(obj.name);
    }
}
class A {
    public String name;
    static A create() {
        var a = new A();
        a.name = "factory created";
        return a;
    }
}