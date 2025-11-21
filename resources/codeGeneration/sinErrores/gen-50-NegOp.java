///0&exitosamente
class NegationTest {
  int testNegation() {
    if (!(5 > 0)) {
      return 1;
    }
    return 0;
  }
}

class Init36 {
  static void main() {
    var nt = new NegationTest();
    debugPrint(nt.testNegation());
  }
}
