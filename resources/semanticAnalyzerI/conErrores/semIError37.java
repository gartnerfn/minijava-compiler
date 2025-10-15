///[Error:m1|10]

abstract class A {
    // No metodo abstracto, sin cuerpo
    abstract void m1();
}

class B extends A {
    // Error: el metodo m1 debe ser declarado como abstract o debe tener cuerpo
    void m1();
}




