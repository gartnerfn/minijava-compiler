///[Error:m1|15]

class A {
    private boolean m1(){
        return true;
    }

    public void m2(){
        m1();
    }
}

//Redefinicion con distinto tipo de retorno
class B extends A {
    private int m1(){
        return 1;}
}






