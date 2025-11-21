///30&exitosamente
class Nested {
  int getBase() {
    return 10;
  }

  int process() {
    return getBase() + getBase() + getBase();
  }
}

class Init5 {
  static void main() {
    var n = new Nested();
    debugPrint(n.process());
  }
}