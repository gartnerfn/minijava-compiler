///10&exitosamente
class Test51 {
    static void main() {
        var obj = new A();
        obj.value = 10;
        var same = obj.getSelf();
        System.printIln(same.value);
    }
}
class A {
    public int value;
    A getSelf() {
        return this;
    }
}