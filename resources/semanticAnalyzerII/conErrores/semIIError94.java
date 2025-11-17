//[Error:g|17]

class A {
    private int g;

    public A m2(int x){
        return new A();
    }
}

class B extends A {
    public void metodo(int x){
        var a = new A();

        a.m2(4).
                m2(4).
                g;
    }
}


class Init{
    static void main()
    {

    }


}


