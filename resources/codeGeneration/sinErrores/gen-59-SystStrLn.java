///hola&mundo&exitosamente
class B{
    int p1;
    String m1(){
        return "hola";
    }
    String m2() {
        return "mundo";
    }

}



class Init{
    static void main()

    {
        var x = new B();
        System.printSln(x.m1());
        System.printSln(x.m2());
    }
}


