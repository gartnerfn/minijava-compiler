///[Error:m1|5]

class A {
    private A m1(int x){
        return m2().m1(x);
    }

    private void m2(){

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

