//[Error:=|8]
class ErrorVoidEnExpresion {
    void metodoVoid() {
        var x = 1;
    }
    
    void metodo() {
        var x = metodoVoid(); // ERROR: void no es un tipo válido para variable
    }
}
