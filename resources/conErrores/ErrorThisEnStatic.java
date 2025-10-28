//[Error:this|6]
class ErrorThisEnStatic {
    int campo;
    
    static void metodo() {
        var x = this; // ERROR: no se puede usar this en método static
    }
}
