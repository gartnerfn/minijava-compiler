///100&exitosamente
class NestedIfTest {
  int nestedCondition(int a, int b) {
    if (a > 0) {
      if (b > 50) {
        return 100;
      }
      return 50;
    }
    return 0;
  }
}

class Init33 {
  static void main() {
    var nit = new NestedIfTest();
    debugPrint(nit.nestedCondition(5, 60));
  }
}
