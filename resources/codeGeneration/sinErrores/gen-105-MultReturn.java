///positive&negative&zero&exitosamente
class Test75 {
    static void main() {
        System.printSln(classify(5));
        System.printSln(classify(-3));
        System.printSln(classify(0));
    }
    static String classify(int n) {
        if (n > 0) {
            return "positive";
        }
        if (n < 0) {
            return "negative";
        }
        return "zero";
    }
}