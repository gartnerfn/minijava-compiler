//[Error:campoB|9]
class MiClase {
    int campoA;
}

class ErrorAtributoNoExisteEnClase {
    void metodo() {
        var obj = new MiClase();
        var x = obj.campoB; // ERROR: campoB no existe en MiClase
    }
}
