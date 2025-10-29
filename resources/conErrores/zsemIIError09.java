///[Error:m1|11]

class A {
    int x;
}

class B extends A{
    int b;

    public B(){
        (this.m1());
    }
}


class Init{
    static void main()
    {
        var a = new A();
        var b = new B();

        var c = a.x;
        var d = b.x;

        var e = a.b;
    }
}

