class A {
    public int g;

    public A m2(int x){
        return new A();
    }
}

class B extends A {
    public void metodo(int x){
        var a = new A();

        var b = a.m2(4).
                m2(4).
                g;

        if(g == 4){
            a.m2(3);
        }
    }
}


class Init{
    static void main()
    {

    }


}


