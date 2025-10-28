//[Error:metodo|11]

class A {
    private int metodo(){
        return 5;
    }

}
class B extends A{
    public void test(){
        var x = metodo();
    }
}



class Init{
    static void main()
    {

    }


}


