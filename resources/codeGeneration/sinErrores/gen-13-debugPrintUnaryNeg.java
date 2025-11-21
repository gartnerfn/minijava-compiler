///-5&exitosamente
class UnaryTest {
    int negate(int x) {
        return -x;
    }
}

class Init {
    static void main() {
        var ut = new UnaryTest();
        debugPrint(ut.negate(5));
    }
}