///50&exitosamente
class Test64 {
    static void main() {
        printValue(new A(50));
    }
    static void printValue(A a) {
        System.printIln(a.value);
    }
}
class A {
    public int value;
    A(int v) {
        value = v;
    }
}