///4&exitosamente
class DecrementTest {
  int testDecrement() {
    var x = 5;
    x = --x;
    return x;
  }
}

class Init31 {
  static void main() {
    var dt = new DecrementTest();
    debugPrint(dt.testDecrement());
  }
}
