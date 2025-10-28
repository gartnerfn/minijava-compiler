//[Error:>=|6]
class ErrorMayorIgual {
    void metodo() {
        var obj1 = new Object();
        var obj2 = new Object();
        var x = obj1 >= obj2; // ERROR: >= solo acepta int
    }
}
