///25&exitosamente
class Test65 {
    static void main() {
        var obj = new A();
        obj.value = 25;
        print(obj.getSelf().value);
    }
    static void print(int n) {
        System.printIln(n);
    }
}
class A {
    public int value;
    A getSelf() {
        return this;
    }
}