//[Error:campo|6]
class ErrorAtributoEnStatic {
    int campo;
    
    static void metodo() {
        var x = campo; // ERROR: no se puede acceder a atributo desde static
    }
}
