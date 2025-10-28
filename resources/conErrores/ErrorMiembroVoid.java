//[Error:campo|8]
class ErrorMiembroVoid {
    void metodoVoid() {
        var x = 1;
    }
    
    void metodo() {
        metodoVoid().campo; // ERROR: void no tiene miembros
    }
}
