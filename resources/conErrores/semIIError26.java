//[Error:;|7]

class A {
    public int hola;
    void m1(){
        var a = new B();
        a.metodo().atributo;
    }

}
class B {
    public C metodo(){
        return new C();
    }
}
class C{
    public int atributo;
}


class Init{
    static void main()
    {

    }


}


