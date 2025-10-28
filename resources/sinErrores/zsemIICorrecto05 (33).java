//[SinErrores]

class TestCombinacionOperadores {
    void metodo() {
        var x = 10;
        var y = 5;
        var resultado1 = x + y * 2;
        var resultado2 = (x + y) * 2;
        var resultado3 = x / y + 3;
        
        var a = true;
        var b = false;
        var logico1 = a && b || !a;
        var logico2 = (a && b) || (!a);
    }
}
