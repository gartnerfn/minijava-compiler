///21&22&23&24&exitosamente

class A{
    int p1;
    int p2;
    int p3;
    int p4;
    public A(int p1, int p2, int p3, int p4){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
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
      int m4() {
        return p3;
    }
        int m5() {
            return p4;
        }


}


class Init{
    static void main()

    { 
        
        var x = new A(21, 22, 23, 24);
        x.m2();        
        debugPrint(x.m2());
        debugPrint(x.m3());
        debugPrint(x.m4());
        debugPrint(x.m5());
    }
}


