//[Error:=|9]
class ErrorAsignacionEncadenadoNoVariable {
    int obtener() {
        return 42;
    }
    
    void metodo() {
        var obj = new ErrorAsignacionEncadenadoNoVariable();
        obj.obtener() = 10; // ERROR: último elemento del encadenado debe ser variable
    }
}
