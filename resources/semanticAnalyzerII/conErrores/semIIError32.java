//[Error:=|5]

class A {
    void m1(){
        new B().m2() = 1;
    }

}

class B{
    int m2(){
        return 2;
    }
}

class Init{
    static void main()
    {

    }


}


