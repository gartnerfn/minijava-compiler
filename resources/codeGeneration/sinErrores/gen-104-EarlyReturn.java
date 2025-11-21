///5&exitosamente
class Test74 {
    static void main() {
        System.printIln(earlyReturn(5));
    }
    static int earlyReturn(int n) {
        if (n > 0) {
            return n;
        }
        return 0;
    }
}