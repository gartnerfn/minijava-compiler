///[SinErrores]
class Main {
    public static void main(String[] args) {
        var numero = 42;        // int
        var nombre = "Facu";    // String
        var lista = List.of(1, 2, 3); // List<Integer>

        for (var x : lista) {   // x se infiere como Integer
            System.out(x);
        }
    }
}