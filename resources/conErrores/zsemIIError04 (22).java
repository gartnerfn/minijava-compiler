//[Error:10|4]
class ErrorCondicionWhile {
    void metodo() {
        while (10) { // ERROR: condición debe ser boolean
            var x = 1;
        }
    }
}
