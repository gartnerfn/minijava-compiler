//[Error:++|5]
class ErrorIncrementoBoolean {
    void metodo() {
        var x = true;
        var y = ++x; // ERROR: ++ solo acepta int
    }
}
