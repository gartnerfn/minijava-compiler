///120&exitosamente
class Test39 {
    static void main() {
        System.printIln(factorial(5));
    }
    static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}