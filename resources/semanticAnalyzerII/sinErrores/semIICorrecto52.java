//[SinErrores]
class A {
    private int num = 5;
    private int metodo(){
        return 3;
    }
    public void otroMetodo(){
        var x = num;
        var z = this.num;
        var t = metodo();
        var y = this.metodo();
    }
}
class B extends A{

}
