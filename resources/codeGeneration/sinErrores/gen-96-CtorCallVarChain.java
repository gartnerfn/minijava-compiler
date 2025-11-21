///99&exitosamente
class Test61 {
    static void main() {
        var x = new A(99).value;
        System.printIln(x);
    }
}
class A {
    public int value;
    A(int v) {
        value = v;
    }
}