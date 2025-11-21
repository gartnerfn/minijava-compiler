///30&exitosamente
class Test52 {
    static void main() {
        var obj = new A();
        obj.value = 10;
        System.printIln(obj.getSelf().getSelf().getValue() * 3);
    }
}
class A {
    public int value;
    A getSelf() {
        return this;
    }
    int getValue() {
        return value;
    }
}