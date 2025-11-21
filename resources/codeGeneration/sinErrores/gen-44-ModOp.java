///2&exitosamente
class ModuloTest {
  int testModulo() {
    return 17 % 5;
  }
}

class Init29 {
  static void main() {
    var mt = new ModuloTest();
    debugPrint(mt.testModulo());
  }
}
