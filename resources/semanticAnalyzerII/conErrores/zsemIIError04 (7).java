//[Error:=|4]
class ErrorAsignacionConstante {
    void metodo() {
        42 = 10; // ERROR: no se puede asignar a una constante
    }
}
