///85&exitosamente
class MultipleAttrs {
  int x;
  int y;

  void setValues(int a, int b) {
    x = a;
    y = b;
  }

  void printBoth() {
    debugPrint(x);
    debugPrint(y);
  }
}

class Init34 {
  static void main() {
    var ma = new MultipleAttrs();
    ma.setValues(8, 5);
    ma.printBoth();
  }
}
