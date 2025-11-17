///[Error:==|8]

class A {
    public boolean m1(){
        var x = new B();
        var y = new C();

        return x == y;
    }
}

class B{

}

class C {

}

class Init{
    static void main()
    { }
}

