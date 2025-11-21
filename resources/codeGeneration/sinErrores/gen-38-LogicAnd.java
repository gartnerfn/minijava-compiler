///1&exitosamente
class LogicAnd {
  int testAnd(int a, int b) {
    if ((a > 0) && (b > 0)) {
      return 1;
    }
    return 0;
  }
}

class Init19 {
  static void main() {
    var la = new LogicAnd();
    debugPrint(la.testAnd(5, 3));
  }
}