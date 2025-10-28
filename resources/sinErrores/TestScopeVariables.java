//[SinErrores]

class TestScopeVariables {
    void metodo() {
        {
            var temp = 1;
        }
        {
            var temp = 2;
        }
    }
}
