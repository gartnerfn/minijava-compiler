///[SinErrores]

interface A {
    public boolean m1();
}

interface B extends A {
    public boolean m1(boolean b);

    public boolean m1(String s, int i);
}