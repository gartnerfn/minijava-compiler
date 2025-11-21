///29&exitosamente
class ExprTest {
    int complex(int a, int b, int c) {
        return (a + b) * c - 3;
    }
}

class Init {
    static void main() {
        var et = new ExprTest();
        debugPrint(et.complex(5, 3, 4));
    }
}