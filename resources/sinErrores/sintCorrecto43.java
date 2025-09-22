///[SinErrores]
class ForNested {
    void f() {
        var i = 0;
        for (i = 0; i < 10; i = i + 1) {
            for (var c: text) {
                var t = c;
            }
        }
    }
}

class ForNested {
    void f() {
        var i = 0;
        for (i; i < 10; i = i + 1) {
            for (var c: text) {
                var t = c;
            }
        }
    }
}