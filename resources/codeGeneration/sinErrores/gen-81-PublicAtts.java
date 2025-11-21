///1&2&3&exitosamente
class Test46 {
    static void main() {
        var obj = new A();
        obj.a = 1;
        obj.b = 2;
        obj.c = 3;
        System.printIln(obj.a);
        System.printIln(obj.b);
        System.printIln(obj.c);
    }
}
class A {
    public int a;
    public int b;
    public int c;
}