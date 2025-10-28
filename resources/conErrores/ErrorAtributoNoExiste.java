//[Error:campo2|6]
class ErrorAtributoNoExiste {
    int campo1;
    
    void metodo() {
        var x = campo2; // ERROR: campo2 no existe
    }
}
