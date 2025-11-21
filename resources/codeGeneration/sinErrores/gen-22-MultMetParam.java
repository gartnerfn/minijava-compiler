///10&20&exitosamente
class Calculator {
  int add(int a, int b) {
    return a + b;
  }

  int multiply(int a, int b) {
    return a * b;
  }
}

class Init3 {
  static void main() {
    var calc = new Calculator();
    debugPrint(calc.add(5, 5));
    debugPrint(calc.multiply(4, 5));
  }
}