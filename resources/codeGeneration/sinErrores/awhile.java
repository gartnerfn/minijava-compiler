//0&1&2&3&4&5&6&7&8&9&exitosamente
class A {

    static void main(){
        var i = 0;

        while (i<10) {
            var b = new B(i);
            System.printIln(b.i);
            i = b.i + 1;
        }

    }

}

class B {

    public int i;

    B (int i) {
        this.i = i;
    }


}