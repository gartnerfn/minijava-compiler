///exitosamente
//ERROR: NullPointerException

class A {
    public int x;

    public A(int a){
        var b = 1;
    }

    public A(String a, int b){
    }

    public void m1(){
    }

    public void m1(int c){
        var e = 3;
        var h = 3;
        var o = 3;
    }

    public static void m2(int y){

    }
}

class B extends A {
    public int x;
    public int z;

    public void m1(){
        var f = 4;
    }
}

class Init{
    static void main()
    {
        var a = new A(5);
        a = null;
        a.m1();
    }
}


