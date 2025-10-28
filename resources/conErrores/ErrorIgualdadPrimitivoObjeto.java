//[Error:==|5]
class ErrorIgualdadPrimitivoObjeto {
    void metodo() {
        var obj = new Object();
        var x = 42 == obj; // ERROR: int y Object no son conformantes
    }
}
