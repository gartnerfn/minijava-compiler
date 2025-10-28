//[Error:algo|5]
class ErrorAccesoAtributoPrimitivo {
    void metodo() {
        var x = 42;
        var y = x.algo; // ERROR: int no tiene atributos
    }
}
