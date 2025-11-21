///5&10&exitosamente
class Test43 {
    static void main() {
        var obj = new A(5, 10);
        System.printIln(obj.x);
        System.printIln(obj.y);
    }
}
class A {
    public int x;
    public int y;
    A(int a, int b) {
        x = a;
        y = b;
    }
}