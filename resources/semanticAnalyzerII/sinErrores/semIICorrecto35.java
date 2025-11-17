//[SinErrores]

class A {
     public B prueba(){
         return new B();
     }
     public void otroMetodo(){
         B.metodoB();
     }
}
class B{
    static void metodoB(){}
}



class Init{
    static void main()
    { }
}


