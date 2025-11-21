///0&exitosamente
class B{
    int p1;
    A a;
    public B(){
        p1 = 11;
        a = null;
    }
    int mz(A a){
        return a.m3();
    }

}
class A{
    int p2;
    public A(){
        p2 = 22;
    }
    int m3(){
        return p2;
    }
}



class Init{
    static void main()
    {
        var b = new B();
        if(b.a == null){
            debugPrint(0);
        }else{
            debugPrint(b.mz(b.a));
        }
    }
}


