///[SinErrores]
class ForNested {
    void f() {
        for (var i = 0; i < 10; i = i + 1) {
            for (var c: text) {
                var t = c;
            }
        }
    }
}