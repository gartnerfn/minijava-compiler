///[Error:m1|11]

class A {
    private boolean m1(){
        return true;
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

