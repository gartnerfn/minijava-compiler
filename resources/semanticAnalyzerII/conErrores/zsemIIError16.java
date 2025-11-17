///[Error:read|17]

class A {
    int x;

}

class B extends A{
    public void m1(){
        this.debugPrint(1);
    }

}

class C extends System{
    public void m2(){
        read(true);
    }
}


