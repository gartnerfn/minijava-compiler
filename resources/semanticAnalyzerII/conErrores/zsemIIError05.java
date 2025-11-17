///[Error:x|5]

class A {
    private boolean m1(int x){
        var x = 10;

        return x > 5;
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

