///1&2&3&exitosamente
class Test80 {
    static void main() {
        var counter = new Counter();
        System.printIln(counter.increment());
        System.printIln(counter.increment());
        System.printIln(counter.increment());
    }
}
class Counter {
    public int count;
    Counter() {
        count = 0;
    }
    int increment() {
        count = count + 1;
        return count;
    }
}