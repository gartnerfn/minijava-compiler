//[Error:+|7]

class A {
    int flag;
    void m1(){
        var c = 2;
        flag = c + null;
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


