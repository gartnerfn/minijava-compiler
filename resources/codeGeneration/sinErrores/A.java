///2&true&z&exitosamente

class A {

    int a;

    static void main(){
        m1(1,new A().boolRet(),'z');
    }

    static void m1(int a, boolean b, char c){
        a = ++a;
        System.printI(a);
        System.printB(b);
        System.printC(c);
    }

    static boolean boolRet(){
        return true;
    }

}