//[Error:==|9]
class ClaseA {}
class ClaseB {}

class ErrorIgualdadNoConformante {
    void metodo() {
        var a = new ClaseA();
        var b = new ClaseB();
        var x = a == b; // ERROR: tipos no relacionados por herencia
    }
}
