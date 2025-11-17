///[Error:b|7]

class A {
    public boolean m1(){
        var x = "a";
        {
            var y = b;
        }

        var b = 5;

        return true;
    }
}

class Init{
    static void main()
    { }
}

