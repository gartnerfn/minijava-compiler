//[Error:campo|8]
class ErrorAccesoAtributoVoid {
    void metodoVoid() {
        var x = 1;
    }
    
    void metodo() {
        var x = metodoVoid().campo; // ERROR: void no tiene atributos
    }
}
