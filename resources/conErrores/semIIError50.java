//[Error:m2|12]

class A {
    int flag;
    void m1(){

    }
    int m2(){
        return 5;
    }
    int m3(){
        flag = m1().m2();
        return 0;
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


