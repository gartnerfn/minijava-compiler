///[Error:m1|4]

class A {
    public static int m1(){
        while(true){
            if(true){
                return 1;
            } else if(false){
                return 2;
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



