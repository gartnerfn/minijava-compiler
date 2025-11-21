///10&exitosamente
class Test42 {
    static void main() {
        var obj = new A(10);
        System.printIln(obj.value);
    }
}
class A {
    public int value;
    A(int v) {
        value = v;
    }
}