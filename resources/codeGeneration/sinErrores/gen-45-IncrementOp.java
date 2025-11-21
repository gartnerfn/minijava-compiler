///6&exitosamente
class IncrementTest {
  int testIncrement() {
    var x = 5;
    x = ++x;
    return x;
  }
}

class Init30 {
  static void main() {
    var it = new IncrementTest();
    debugPrint(it.testIncrement());
  }
}
