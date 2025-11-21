//Ver los offsets&exitosamente

class C{

    private C c;
    int a;

     void m1(){
        System.printSln("m1 de C");
    }
    void m2(){
        System.printSln("m2 de C");
    }


}

class B extends C{

    int b;

     void m3(){
        System.printSln("m3 de B");
    }

     void m2(){
        System.printSln("m2 de B");
    }

}

class A extends B{

    private String e;


     void m4(){
        System.printSln("m4 de A");
    }

     void m3(){
        System.printSln("m3 de A");
    }

}

class Init {
    static void main(){
        System.printSln("Ver los offsets");
        var a = new A();
        a.m1();
        a.m2();
        a.m3();
        a.m4();
    }
}