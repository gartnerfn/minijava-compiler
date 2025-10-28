//[Error:+|4]
class ErrorMezclaOperadores {
    void metodo() {
        var x = 5 + true; // ERROR: tipos incompatibles en operación
    }
}
