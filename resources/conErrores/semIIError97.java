//[Error:C|9]

abstract class C {
    public abstract void m1();
}

class B  {
    public void metodo(int x){
        var c = new C();
        c.m1();
    }
}


class Init{
    static void main()
    {

    }

}


