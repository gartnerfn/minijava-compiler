///1234&exitosamente

class A {
    public static void m1(int a){

    }

    public static void m1(int a, String b){

    }
}

class Init{
    public Init(int a){

    }

    public Init(int a, int b){

    }

    static void main()
    { 
        debugPrint(1234);

        A.m1(5);
        A.m1(10, "hello");
    }
}


