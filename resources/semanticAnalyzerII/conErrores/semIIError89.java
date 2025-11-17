//[Error:=|8]

class A {
    public int metodo(boolean b, int a){
        (b
                =
                false)
                =
                true;

        return 4;
    }


}

class B extends A {
    public void metodo(int x){
        var a = new A();
        a.metodo(14);
    }
}




class Init{
    static void main()
    {

    }


}


