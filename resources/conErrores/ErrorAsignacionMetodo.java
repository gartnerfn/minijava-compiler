//[Error:=|8]
class ErrorAsignacionMetodo {
    int obtener() {
        return 42;
    }
    
    void metodo() {
        obtener() = 10; // ERROR: no se puede asignar a un método
    }
}
