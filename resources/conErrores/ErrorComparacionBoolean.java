//[Error:<|4]
class ErrorComparacionBoolean {
    void metodo() {
        var x = true < false; // ERROR: < solo acepta int
    }
}
