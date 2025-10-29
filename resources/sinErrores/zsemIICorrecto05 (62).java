//[Error:A|9]

class A {
    public int a;

    private A(){
        this.a = 4;
    }

    public A m2(int x){
        return new A();
    }
}

class B extends A {
    public void metodo(int x){

    }
}


class Init{
    static void main()
    {

    }


}


