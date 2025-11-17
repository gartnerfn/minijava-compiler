//[SinErrores]

class TestVariablesLocales {
    void metodo(int param1, boolean param2) {
        var local1 = 10;
        var local2 = param1 + 5;
        var local3 = param2 && true;
        var resultado = local1 + local2;
    }
}