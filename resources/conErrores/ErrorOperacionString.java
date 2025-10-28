//[Error:+|6]
class ErrorOperacionString {
    void metodo() {
        var s1 = "hola";
        var s2 = "mundo";
        var suma = s1 + s2; // ERROR: + solo acepta int (no hay concatenación en MiniJava)
    }
}
