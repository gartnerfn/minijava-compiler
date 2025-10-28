//[SinErrores]

class TestLlamadasDirectas {
    int suma(int a, int b) {
        return a + b;
    }
    
    void metodo() {
        var resultado = suma(5, 3);
        suma(10, 20);
    }
}
