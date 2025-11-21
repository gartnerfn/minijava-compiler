///22&exitosamente
class B{
    int p1;
    int mz(A a){
        return a.m3();
    }

}
class A extends B{
    int p2;
    public A(){
        p2 = 22;
        p1 = 6;
    }
      void m1(int p1){
        debugPrint(p1);
    }
      int m2() {
        return p1;
      }
      int m3() {
        return p2;
      }



}


class Init{
    static void main()

    {
        debugPrint(x.mz(new A()));
    }
}


