///10&exitosamente
class WhileLoop {
  int countTo10() {
    var i = 0;
    while (i < 10) {
      i = i + 1;
    }
    return i;
  }
}

class Init13 {
  static void main() {
    var wl = new WhileLoop();
    debugPrint(wl.countTo10());
  }
}
