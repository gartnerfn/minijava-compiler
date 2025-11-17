//[Error:if|5]

class A {
    public int metodo(boolean b, int a){
        if(
                m1()){

        }
    }

    public void m1(){

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


