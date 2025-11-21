///100&exitosamente
class Test47 {
    static void main() {
        var obj = new A(100);
        System.printIln(obj.value);
    }
}
class A {
    public int value;
    A(int value) {
        this.value = value;
    }
}