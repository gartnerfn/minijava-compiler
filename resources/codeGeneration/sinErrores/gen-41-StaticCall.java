///42&exitosamente
class StaticMathUtil {
  static int staticAdd(int a, int b) {
    return a + b;
  }
}

class Init26 {
  static void main() {
    debugPrint(StaticMathUtil.staticAdd(20, 22));
  }
}
