//[Error:+|6]
class ErrorOperacionChar {
    void metodo() {
        var c1 = 'a';
        var c2 = 'b';
        var suma = c1 + c2; // ERROR: + solo acepta int, no char
    }
}
