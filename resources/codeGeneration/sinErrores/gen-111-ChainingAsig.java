///200&250&251&exitosamente
class Test58 {
    static void main() {
        var obj = new A();
        obj.value = 100;
        obj.getSelf().value = 200;
        System.printIln(obj.getSelf().value);
        obj.getSelf().value = obj.getSelf().value + 50;
        System.printIln(obj.getSelf().value);
        obj.getSelf().value = obj.getSelf().getValue() + 1;
        System.printIln(obj.getSelf().getValue());

    }
}
class A {
    public int value;
    A getSelf() {
        return this;
    }
    int getValue() {
        return this.value;
    }
}