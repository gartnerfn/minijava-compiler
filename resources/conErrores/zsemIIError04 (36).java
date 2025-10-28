//[Error:metodoInstancia|8]
class ErrorMetodoInstanciaEnStatic {
    void metodoInstancia() {
        var x = 1;
    }
    
    static void metodoStatic() {
        metodoInstancia(); // ERROR: no se puede llamar método de instancia
    }
}
