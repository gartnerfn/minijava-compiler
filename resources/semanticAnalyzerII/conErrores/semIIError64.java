//[Error:metodo|14]

class A {
    private int metodo() {
        return 3;
    }
}
class B extends A{

}
class C{
    B b = new B();
    public void metodo(){
        var x = b.metodo();
    }
}



class Init{
    static void main()
    {

    }


}


