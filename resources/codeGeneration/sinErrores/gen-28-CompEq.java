///1&exitosamente
class Compare {
  int checkEqual(int a, int b) {
    if (a == b) {
      return 1;
    }
    return 0;
  }
}

class Init9 {
  static void main() {
    var cmp = new Compare();
    debugPrint(cmp.checkEqual(5, 5));
  }
}