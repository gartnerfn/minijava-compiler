//[Error:=|6]
class ErrorAsignacionExpresion {
    void metodo() {
        var x = 5;
        var y = 3;
        (x + y) = 10; // ERROR: no se puede asignar a una expresión
    }
}
