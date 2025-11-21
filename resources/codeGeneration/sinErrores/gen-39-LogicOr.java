///1&exitosamente
class LogicOr {
  int testOr(int a, int b) {
    if ((a > 0) || (b > 0)) {
      return 1;
    }
    return 0;
  }
}

class Init20 {
  static void main() {
    var lo = new LogicOr();
    debugPrint(lo.testOr(0, 5));
  }
}
