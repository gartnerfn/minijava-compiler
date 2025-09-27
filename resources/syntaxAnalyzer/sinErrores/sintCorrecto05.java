///[SinErrores]
// Prueba un bloque con una asignacion, un atributo y un constructor con visibilidad opcional

class Prueba1 {
    Prueba1(){}
    public Prueba1(){}
    private Prueba1(){}

    void metodo(){}
    public void metodo(){}
    private void metodo(){}

    static void metodo(){}
    public static void metodo(){}
    private static void metodo(){}

    final void metodo();
    public final void metodo();
    private final void metodo();

    abstract void metodo();
    public abstract void metodo();
    private abstract void metodo();
}

