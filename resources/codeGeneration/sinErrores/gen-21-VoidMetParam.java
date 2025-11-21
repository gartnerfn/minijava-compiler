///42&exitosamente
class B {
  void printNum(int n) {
    debugPrint(n);
  }
}

class Init2 {
  static void main() {
    var obj = new B();
    obj.printNum(42);
  }
}