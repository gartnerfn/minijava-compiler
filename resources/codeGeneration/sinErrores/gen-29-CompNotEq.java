///1&exitosamente
class CompareNE {
  int checkNotEqual(int a, int b) {
    if (a != b) {
      return 1;
    }
    return 0;
  }
}

class Init10 {
  static void main() {
    var cmp = new CompareNE();
    debugPrint(cmp.checkNotEqual(5, 3));
  }
}
