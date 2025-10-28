//[SinErrores]

class Animal {}
class Perro extends Animal {}

class TestReturnHerencia {
    Animal metodo() {
        return new Perro();
    }
}