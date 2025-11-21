///5&exitosamente
class B{
    int p1;
    void m1(int p1){
        debugPrint(p1);
    }

}
class A extends B{
    int p2;
    public A(){
        p2 = 22;
        p1 = 6;
    }
      void m1(int p1){
        debugPrint(5);
    }
      int m2() {
        return p1;
      }



}


class Init{
    static void main()

    {
        var x = new A();
        x.m1(6);
    }
}


