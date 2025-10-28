//[Error:procesar|11]
class Animal3 {}
class Pajaro extends Animal3 {}

class ErrorParametroHerenciaInversa {
    void procesar(Pajaro p) {
        var x = 1;
    }
    
    void llamada() {
        procesar(new Animal3()); // ERROR: Animal3 no conforma con Pajaro
    }
}
