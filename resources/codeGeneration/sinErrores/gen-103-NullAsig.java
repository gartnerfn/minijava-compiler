///not null&is null&exitosamente
class Test73 {
    static void main() {
        var obj = new A();
        if (obj != null) {
            System.printSln("not null");
        }
        obj = null;
        if (obj == null) {
            System.printSln("is null");
        }
    }
}
class A { }