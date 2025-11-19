//[SinErrores]

class A {
    public static int metodo(){
        while(true){
            return 1;
        }
        var a = metodo();
        return 1;
    }
}

class Init{
    static void main()
    {
        A.metodo();
    }

}





