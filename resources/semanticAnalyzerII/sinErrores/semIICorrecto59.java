//[SinErrores]

class A {
    String hola = "hola";

    public A(){

    }

    public A(int x){

    }

    public void m1(){
        hola = "chau";
    }
}

class Init{
    A a;
    A b;

    void main() {
       a = new A(5);
       b= new A();
       a = new A();
    }

    static void main2(){
        var a = new A();
    }
}


