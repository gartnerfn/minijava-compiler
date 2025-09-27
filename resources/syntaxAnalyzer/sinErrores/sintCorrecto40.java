///[SinErrores]
class ForClassic {
    void f() {
        for (var i = 0; i < 10; i = i + 1) {
            var a = i;
        }
    }
}

class ForClassic {
    void f() {
        var i = 0;
        for (i = 0; i < 10; i = i + 1) {
            var a = i;
        }
    }
}

class ForClassic {
    void f() {
        var i = 0;
        for (i; i < 10; i = i + 1) {
            var a = i;
        }
    }
}