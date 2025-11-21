///10&exitosamente
class Test60 {
    static void main() {
        System.printIln(new A(10).getValue());
    }
}
class A {
    public int value;
    A(int v) {
        value = v;
    }
    int getValue() {
        return value;
    }
}