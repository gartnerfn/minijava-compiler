///50&exitosamente
class IfElse {
  int conditional(int val) {
    if (val > 25) {
      return 50;
    } else {
      return 25;
    }
  }
}

class Init14 {
  static void main() {
    var ie = new IfElse();
    debugPrint(ie.conditional(30));
  }
}
