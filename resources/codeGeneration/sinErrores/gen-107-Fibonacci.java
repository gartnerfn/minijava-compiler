///55&exitosamente
class Test77 {
    static void main() {
        System.printIln(fib(10));
    }
    static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
}