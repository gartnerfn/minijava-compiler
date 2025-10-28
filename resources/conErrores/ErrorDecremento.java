//[Error:--|5]
class ErrorDecremento {
    void metodo() {
        var x = 'a';
        --x; // ERROR: -- solo acepta int
    }
}
