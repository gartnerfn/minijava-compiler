///[Error:=|5]

class A {
    public boolean m1(){
        var x = m2();
    }

    public void m2(){
        return;
    }
}

class Init{
    static void main()
    { }
}

