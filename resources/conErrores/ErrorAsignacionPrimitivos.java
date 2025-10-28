//[Error:=|5]
class ErrorAsignacionPrimitivos {
    void metodo() {
        var x = 10;
        x = true; // ERROR: boolean no conforma con int
    }
}
