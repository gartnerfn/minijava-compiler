//[Error:metodo|11]

class A {
    private int metodo() {
        return 3;
    }
}
class B{
    A a = new A();
    public void metodoB() {
        var x = a.metodo();
    }
}



class Init{
    static void main()
    {

    }


}


