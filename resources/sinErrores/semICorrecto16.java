///[SinErrores]

abstract class A {
    public abstract int m1();

    public abstract char m2();
}

abstract class B extends A {
    public int m1() {
        return 0;
    }

    public abstract char m2();
}


