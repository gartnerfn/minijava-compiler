//[Error:;|7]
class Animal2 {}
class Gato extends Animal2 {}

class ErrorReturnHerenciaInversa {
    Gato metodo() {
        return new Animal2(); // ERROR: Animal2 no conforma con Gato
    }
}
