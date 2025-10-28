//[SinErrores]

class TestIf {
    void metodo(int x) {
        if (x > 0) {
            var y = x + 1;
        }
        
        if (x < 0) {
            var z = -x;
        } else {
            var w = x;
        }
        
        if (x == 0) {
            var a = 1;
        } else {
            if (x > 0) {
                var b = 2;
            } else {
                var c = 3;
            }
        }
    }
}