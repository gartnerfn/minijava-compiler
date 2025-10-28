class A {
    public boolean m1(){
        return true;
    }

    public boolean m2(){
        return m1();
    }
}

class B extends A{

}

class Init{
    static void main()
    { }
}

