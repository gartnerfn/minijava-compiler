//[SinErrores]

class Nodo {
    int dato;
    Nodo siguiente;
    
    Nodo getSiguiente() {
        return siguiente;
    }
}

class TestEncadenamientoComplejo {
    void metodo() {
        var n1 = new Nodo();
        var n2 = new Nodo();
        n1.siguiente = n2;
        var dato = n1.siguiente.dato;
        var dato2 = n1.getSiguiente().dato;
        var dato3 = n1.getSiguiente().getSiguiente();
    }
}