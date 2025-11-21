///15&exitosamente
class Test50 {
    static void main() {
        var obj = new A();
        obj.value = 10;
        System.printIln(obj.addTo(5));
    }
}
class A {
    public int value;
    int addTo(int n) {
        return value + n;
    }
}