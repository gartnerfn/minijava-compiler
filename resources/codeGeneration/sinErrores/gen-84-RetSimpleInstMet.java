///10&exitosamente
class Test49 {
    static void main() {
        var obj = new A();
        obj.value = 10;
        System.printIln(obj.getValue());
    }
}
class A {
    public int value;
    int getValue() {
        return value;
    }
}