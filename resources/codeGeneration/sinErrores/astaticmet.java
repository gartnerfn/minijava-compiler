//0&1&2&3&4&5&6&7&8&9&exitosamente
class A {

    static void main(){
        A.m0();
        B.m0();
        B.m1();
        new A().m0();
        new B().m0();
    }

    static void m0(){
        System.printSln("m0 estat de A");
    }


}

class B extends A {




    static void m1(){
        System.printSln("m1 estat de B");
    }

}