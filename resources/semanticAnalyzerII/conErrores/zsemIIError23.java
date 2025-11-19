///[Error:m1|4]

class A {
    public static int m1(){
        while(false){
            if(true){
                return 1;
            } else if(false){
                return 2;
            } else {
                return 3;
            }
        }
    }
}
class Init{
    static void main()
    {
        A.m1();
    }
}

