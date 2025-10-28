//[Error:procesar2|22]
class Animal {

}
class Perro extends Animal {

}

class TestParametrosHerencia {
    Animal p = new Perro();
    void procesar(Animal a) {
        var x = 1;
    }
    void procesar2(Perro p) {
        var y = 2;
    }
    
    void metodo() {
        var a = new Animal();
        procesar(a);
        procesar(new Perro());
        procesar2(p);
    }
}