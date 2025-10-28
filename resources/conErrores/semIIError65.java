//[Error:att|12]

class A {
    private int att;
}
class B extends A{

}
class C{
    B b = new B();
    public void metodo(){
        var x = b.att;
    }
}



class Init{
    static void main()
    {

    }


}


