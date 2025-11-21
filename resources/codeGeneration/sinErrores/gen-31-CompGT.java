///1&exitosamente
class CompareGT {
  int checkGreater(int a, int b) {
    if (a > b) {
      return 1;
    }
    return 0;
  }
}

class Init12 {
  static void main() {
    var cmp = new CompareGT();
    debugPrint(cmp.checkGreater(7, 4));
  }
}