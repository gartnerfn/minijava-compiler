///[Error:'a'|10]

class A {
    public int m1( int a){
        return a + 10;
    }

    public int m2(){
        return m1(
                'a'
        );
    }
}

class B{

}

class C {

}

class Init{
    static void main()
    { }
}

