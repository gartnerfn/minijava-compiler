//[SinErrores]
class A {
    private int att;
}
class B extends A{
    public int att;
}
class C {
    public void metodo(){
        var b = new B();
        var x = b.att;
    }
}