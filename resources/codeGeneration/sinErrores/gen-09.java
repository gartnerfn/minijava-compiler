///5&hello&10&exitosamente

class A {
    public A(int a){
        debugPrint(a);
    }

    public A(String a, int b){
        System.printSln(a);
        debugPrint(b);
    }
}

class Init{
    public Init(int a){

    }

    public Init(int a, int b){

    }

    static void main()
    { 
        var a = new A(5);
        var b = new A("hello", 10);

    }
}


