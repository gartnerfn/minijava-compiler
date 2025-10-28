//[Error:;|7]
class Vehiculo3 {}
class Casa3 {}

class ErrorReturnObjeto {
    Vehiculo3 metodo() {
        return new Casa3(); // ERROR: Casa3 no conforma con Vehiculo3
    }
}
