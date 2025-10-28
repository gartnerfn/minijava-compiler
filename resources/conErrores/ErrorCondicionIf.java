//[Error:42|4]
class ErrorCondicionIf {
    void metodo() {
        if (42) { // ERROR: condición debe ser boolean
            var x = 1;
        }
    }
}
