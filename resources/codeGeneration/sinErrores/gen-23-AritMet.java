///15&exitosamente
class Math1 {
  int compute() {
    var x = 10;
    var y = 5;
    return x + y;
  }
}

class Init4 {
  static void main() {
    var m = new Math1();
    debugPrint(m.compute());
  }
}