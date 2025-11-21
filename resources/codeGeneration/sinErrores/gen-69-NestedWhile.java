///0-0&0-1&1-0&1-1&exitosamente
class Test31 {
    static void main() {
        var i = 0;
        while (i < 2) {
            var j = 0;
            while (j < 2) {
                System.printI(i);
                System.printC('-');
                System.printIln(j);
                j = j + 1;
            }
            i = i + 1;
        }
    }
}