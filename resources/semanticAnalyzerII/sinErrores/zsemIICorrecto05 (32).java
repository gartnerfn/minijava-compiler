//[SinErrores]

class TestBloques {
    void metodo() {
        var x = 1;
        {
            var y = 2;
            var z = x + y;
        }
        {
            var y = 3;
            var w = x + y;
        }
    }
}