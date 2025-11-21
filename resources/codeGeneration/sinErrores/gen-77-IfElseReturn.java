///positive&exitosamente
class Test40 {
    static void main() {
        System.printSln(sign(5));
    }
    static String sign(int n) {
        if (n > 0) {
            return "positive";
        } else {
            return "non-positive";
        }
    }
}