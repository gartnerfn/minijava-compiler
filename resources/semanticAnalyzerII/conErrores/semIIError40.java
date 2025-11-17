//[Error:!=|8]

class A {
    boolean flag;
    void m1(){
        var b = new B();
        var c = 5;
        flag = c != b;
    }

}

class B{
    
}
class C extends B{

}

class Init{
    static void main()
    {

    }


}


