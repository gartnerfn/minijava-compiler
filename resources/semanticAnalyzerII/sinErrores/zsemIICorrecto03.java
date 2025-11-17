class A {
    boolean m1 = metodo();

    private boolean metodo(){
        if(false){
            return false;
        } else {
            return true;
        }
    }
}

class B extends A{

}

class Init{
    static void main()
    { }
}

