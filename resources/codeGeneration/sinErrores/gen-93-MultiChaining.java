///100&exitosamente
class Test58 {
    static void main() {
        var obj = new A();
        obj.value = 100;
        System.printIln(obj.getSelf().getSelf().getSelf().value);
    }
}
class A {
    public int value;
    A getSelf() {
        return this;
    }
}