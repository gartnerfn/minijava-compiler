///[SinErrores]

interface A {
    boolean b;
    int i;
    char c;
    String s;

    boolean m1();
    abstract int m2();
    static void m(int i, char c, String s, boolean b){}
    char m3(boolean a, int i, char c);
}

interface B extends A {
    int m2(boolean b);
}

interface C extends B {
    boolean b;
    int i;
}