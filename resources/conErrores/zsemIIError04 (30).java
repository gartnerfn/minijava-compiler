//[Error:metodo|5]
class ErrorLlamadaMetodoPrimitivo {
    void metodo() {
        var x = 42;
        x.metodo(); // ERROR: int no tiene métodos
    }
}
