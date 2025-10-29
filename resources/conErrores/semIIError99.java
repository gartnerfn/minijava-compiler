//[Error:m1|13]

abstract class C {
    public abstract void m1();
}

class B  {
    public void metodo(int x){
        return;
    }

    public void m2(){
        m1().algo();
    }
}


class Init{
    static void main()
    {

    }

}


