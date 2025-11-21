//0&1&2&3&4&5&6&7&8&9&exitosamente

class A {

    static void main(){
        var string1 = new String();
        string1 = "hola";

        var clB = new B();
        clB.leerString(string1);

        System.printSln("Pruebo que no cambio con el cambio de parametro");
        System.printSln(string1);

        string1 = "test cambio var";
        System.printSln("Cambio la variable y pruebo leerla desde A");
        System.printSln(string1);

        clB.leerString(string1);

    }


}

class B {

    void leerString(String s){
        System.printSln("Arranca leerString()");
        System.printSln(s);
        System.printSln("Fin de string leido");
        s = "Test cambiar parametro";
        System.printSln(s);
    }


}