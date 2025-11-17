///[Error:m1|9]

class Int {

}

class A {
    private A m1(int x){
        return m2().m1(x);
    }

    private int m2(){
        return 4;
    }
}

class B {
    public boolean m2(){
        return m1();
    }
}

class C {

}

class Init{
    static void main()
    { }
}

