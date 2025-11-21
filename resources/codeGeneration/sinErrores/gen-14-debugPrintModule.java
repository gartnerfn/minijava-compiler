///2&exitosamente
class ModTest {
    int modulo(int a, int b) {
        return a % b;
    }
}

class Init {
    static void main() {
        var mt = new ModTest();
        debugPrint(mt.modulo(11, 3));
    }
}