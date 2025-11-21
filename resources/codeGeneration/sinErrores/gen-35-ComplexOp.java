///25&exitosamente
class Complex {
  int calculate(int x, int y) {
    var temp = (x + y) * 2;
    return temp - 5;
  }
}

class Init16 {
  static void main() {
    var cx = new Complex();
    debugPrint(cx.calculate(10, 5));
  }
}
