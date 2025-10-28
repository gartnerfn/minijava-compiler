//[Error:>|6]
class ErrorComparacionString {
    void metodo() {
        var s1 = "hola";
        var s2 = "mundo";
        var mayor = s1 > s2; // ERROR: > solo acepta int
    }
}
