//[Error:procesar|11]
class Vehiculo {}
class Casa {}

class ErrorTipoParametroObjeto {
    void procesar(Vehiculo v) {
        var x = 1;
    }
    
    void llamada() {
        procesar(new Casa()); // ERROR: Casa no conforma con Vehiculo
    }
}
