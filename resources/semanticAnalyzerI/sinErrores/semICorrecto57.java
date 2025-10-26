///[SinErrores]

class Z {
    boolean b;

    private int m1(){

    }

    int m2(){

    }
    int m2(boolean a){}
    int m2(int a, char c){}
}

class A extends Z {
    int m1(){

    }

    int m2(){

    }

    int m2(boolean a){}
    int m2(int a, char c){}
}

class B extends A {

}
