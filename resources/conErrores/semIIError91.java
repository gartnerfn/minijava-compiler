//[Error:a|14]

class A {
    public boolean m2(int x){
        if(x > 2) {
            return true;
        } else
        if (x < -10){
            return false;
        } else {
            return true;
        }

        var a = "hola";
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


