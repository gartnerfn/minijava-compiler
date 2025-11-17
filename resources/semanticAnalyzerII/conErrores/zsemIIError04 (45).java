//[Error:!|5]
class ErrorNegacionInt {
    void metodo() {
        var x = 42;
        var y = !x; // ERROR: ! solo acepta boolean
    }
}
