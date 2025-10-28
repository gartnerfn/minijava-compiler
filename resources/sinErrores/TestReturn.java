//[SinErrores]

class TestReturn {
    int metodoInt() {
        return 42;
    }
    
    boolean metodoBoolean() {
        return true;
    }
    
    void metodoVoid() {
        return;
    }
    
    Punto metodoObjeto() {
        return new Punto();
    }
    
    Punto metodoNull() {
        return null;
    }
}
class Punto {
    int x;
    int y;
}