///exitosamente


class A {
    int a;

    public static void main(){
        var c = new C();
        c.a = 5;
    }
}

class B extends A{
    int b;
    int a;
}

class C extends B {
    int c;
}


