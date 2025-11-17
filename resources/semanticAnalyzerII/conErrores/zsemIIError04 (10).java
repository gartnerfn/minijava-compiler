//[Error:=|8]
class Animal {}
class Perro extends Animal {}

class ErrorAsignacionHerenciaInversa {
    void metodo() {
        var perro = new Perro();
        perro = new Animal(); // ERROR: Animal no conforma con Perro (herencia inversa)
    }
}
