//[Error:x|7]
class ErrorScopeBloque {
    void metodo() {
        {
            var x = 10;
        }
        var y = x; // ERROR: x no está en scope
    }
}
