///[SinErrores]

abstract class A {
    boolean b;
    int i;
    char c;
    B attr;
    String s;

    boolean m1(){}
    abstract int m2();
    static void m(int i, char c, String s, boolean b){}
    final B m3(boolean a, int i, char c, B attr){ return 1;  }
}

class B extends A {
    int m2() {

    }
}

class C extends B {
    boolean b;
    int i;
}