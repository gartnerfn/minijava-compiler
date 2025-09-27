///[SinErrores]

class Persona {
    Persona() { var y = 5; }
    public int edad() { return 42; }
}

final class Persona {
    Persona() { var y = 5; }
    public int edad() { return 42; }
}

static class Persona {
    Persona() { var y = 5; }
    public int edad() { return 42; }
}

abstract class Calc {
    static void reset() { ; }
}