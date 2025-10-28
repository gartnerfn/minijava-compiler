//[SinErrores]
class A {
    boolean flag = true;
    void m1(){
        var b = new B();
        var c = new C();
        flag = b == c;
        flag = b != c;
        flag = b == null;
        flag = c != null;
        flag = null == b;
        flag = null != c;
        flag = null == null;
    }

}
class B {

}
class C extends B{

}

class Init{
    static void main()
    { }
}


