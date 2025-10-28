//[SinErrores]
class A {
    private void metodo(){

    }
}
class B extends A{
    public void metodo(){

    }
}
class C {
    public void metodo(){
        var b = new B();
        b.metodo();
    }
}