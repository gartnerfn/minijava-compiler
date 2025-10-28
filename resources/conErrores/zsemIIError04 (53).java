//[Error:|||4]
class ErrorOrLogico {
    void metodo() {
        var x = true || 42; // ERROR: || solo acepta boolean
    }
}
