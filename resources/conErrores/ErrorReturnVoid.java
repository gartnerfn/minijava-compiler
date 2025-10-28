//[Error:;|4]
class ErrorReturnVoid {
    void metodo() {
        return 42; // ERROR: método void no debe retornar valor
    }
}
