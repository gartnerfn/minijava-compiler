///55&exitosamente
class MultiVar {
  int sum() {
    var a = 10;
    var b = 20;
    var c = 25;
    return a + b + c;
  }
}

class Init15 {
  static void main() {
    var mv = new MultiVar();
    debugPrint(mv.sum());
  }
}

