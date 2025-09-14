///[SinErrores]
// Prueba un bloque con una asignacion, un atributo y un constructor con visibilidad opcional

class Prueba1 {
    Prueba1(){

    }

    void metodo(){
       a = 5;
    }

    public void metodo(){
        a = 5;
    }

    private void metodo(){
        a = 5;
    }

    static void metodo(){

    }

    final void metodo();

    abstract void metodo();
    
}

