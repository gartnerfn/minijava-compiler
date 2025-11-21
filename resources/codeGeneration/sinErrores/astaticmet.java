//0&0&1&0&0&exitosamente
class A {

    static void main(){
        A.m0();
        B.m0();
        B.m1();
        new A().m0();
        new B().m0();
    }

    static void m0(){
        System.printSln("0");
    }


}

class B extends A {




    static void m1(){
        System.printSln("1");
    }

}