//[SinErrores]
class Animal {

}
class Perro extends Animal {

}

class TestAsignacionesHerencia {
    void metodo() {
        var animal = new Animal();
        var perro = new Perro();
        animal = perro;
        animal = new Perro();
    }
}