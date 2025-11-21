//andan&1&equals y not equals con objs tmb&exitosamente

class A{

    static void main() {

        if ((1 < 5) && (-1 > -5) && (5 == (3+2)) && (5 >= 5) && !(8 <= 5))
            System.printSln("andan");

        System.printIln(11%2);

        var x = new A();
        var y = x;

        if ((y == x) && (x == y) && (x != new A()))
            System.printSln("equals y not equals con objs tmb");
    }


}