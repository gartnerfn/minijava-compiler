//[Error:=|8]
class Vehiculo2 {}
class Casa2 {}

class ErrorAsignacionObjetos {
    void metodo() {
        var v = new Vehiculo2();
        v = new Casa2(); // ERROR: Casa2 no conforma con Vehiculo2
    }
}
