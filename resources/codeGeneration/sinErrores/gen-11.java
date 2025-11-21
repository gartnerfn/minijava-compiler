///exitosamente
//ERROR: NullPointerException

class A {
    public int x;

    public A(int a){
        x = 1;
        var b = 1;
    }

    public A(String a, int b){
    }

    public int m1(){
        return 3;
    }

    public int m1(int c){
        var e = 3;
        var h = 3;
        var o = 3;

        return h;
    }

    public static void m2(int y){

    }
}

class B extends A {
    public int x;
    public int z;

    public B(int a){
        x = a;
    }

    public int m1(){
        return x;
    }
}

class Init{
    static void main()
    {
        var b = new B(5);
        debugPrint(b.m1());
    }
}


