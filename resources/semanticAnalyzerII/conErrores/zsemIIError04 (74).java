//[Error:x|5]
class ErrorVariableDuplicada {
    void metodo() {
        var x = 10;
        var x = 20; // ERROR: x ya fue declarada
    }
}
