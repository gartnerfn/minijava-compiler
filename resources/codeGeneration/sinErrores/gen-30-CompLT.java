///1&exitosamente
class CompareLT {
  int checkLess(int a, int b) {
    if (a < b) {
      return 1;
    }
    return 0;
  }
}

class Init11 {
  static void main() {
    var cmp = new CompareLT();
    debugPrint(cmp.checkLess(3, 5));
  }
}
