//[Error:=|4]
class ErrorTipoNull {
    void metodo() {
        var x = null; // ERROR: no se puede inferir tipo de null
    }
}
