//[Error:=|6]

class A {
    void m1(){
        var b = new B();
        b.m2() = 7;
    }


}

class B{
    int m2(){
        return 5;
    }
}

class Init{
    static void main()
    {

    }


}


