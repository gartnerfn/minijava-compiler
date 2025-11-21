///1&exitosamente
class LETest {
    int lessThanOrEqual(int a, int b) {
        if (a <= b) {
            return 1;
        }
        return 0;
    }
}

class Init {
    static void main() {
        var le = new LETest();
        debugPrint(le.lessThanOrEqual(5, 5));
    }
}