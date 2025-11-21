///0&true&z&exitosamente

class A {

    int a;

    static void main(){
        new A().m1(1,new A().boolRet(),'z');
    }

    void m1(int a, boolean b, char c){
        a = --a;
        System.printI(a);
        System.printB(b);
        System.printC(c);
    }

    boolean boolRet(){
        return true;
    }

}