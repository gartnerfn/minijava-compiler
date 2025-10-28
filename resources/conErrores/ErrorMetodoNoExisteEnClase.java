//[Error:metodoInexistente|9]
class MiClase2 {
    int campoA;
}

class ErrorMetodoNoExisteEnClase {
    void metodo() {
        var obj = new MiClase2();
        obj.metodoInexistente(); // ERROR: método no existe en MiClase2
    }
}
