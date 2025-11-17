//[Error:m2|14]

class A {
    public A m2(int x){
        return new A();
    }
}

class B extends A {
    public void metodo(int x){
        var a = new A();

        a.m2(4).
                m2(true);
    }
}


class Init{
    static void main()
    {

    }


}


