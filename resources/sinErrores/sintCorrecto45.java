///[SinErrores]
class TernarioSimple {
    int f(int a) {
        return a > 0 ? 1 : -1;
    }
}

class TernarioEnVar {
    void f() {
        var x = true ? 10 : 20;
        if (2) {
            ;
        }
    }
}

class TernarioEncadenado {
    int f(int a) {
        return a == 0 ? 0 : a > 0 ? 1 : -1;
    }
}

class TernarioConAsignacion {
    void f() {
        var y = false ? (x = 1) : (x = 2);
    }
}

class TernarioConLlamadas {
    void f() {
        var z = check() ? foo(1) : foo(2);
    }
}

class TernarioConLlamadas {
    void f() {
        check() ? foo(1) : foo(2);
    }
}

class TernarioConLlamadas {
    void f() {
        2 + 2 == 5 ? foo(1) : foo(2);
    }
}