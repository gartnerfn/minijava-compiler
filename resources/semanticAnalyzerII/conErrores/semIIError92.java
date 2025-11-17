//[Error:m2|11]

class A {
    public static boolean m2(int x){
        return true;
    }
}

class B extends A {
    public void metodo(int x){
        A.m2(true);
    }
}




class Init{
    static void main()
    {

    }


}


