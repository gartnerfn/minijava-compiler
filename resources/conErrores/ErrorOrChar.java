//[Error:|||4]
class ErrorOrChar {
    void metodo() {
        var x = 'a' || 'b'; // ERROR: || solo acepta boolean
    }
}
