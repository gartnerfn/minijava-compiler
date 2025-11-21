///150&exitosamente
class MultiAttr {
  int x;
  int y;
  int z;

  void init(int a, int b, int c) {
    x = a;
    y = b;
    z = c;
  }

  int sum() {
    return x + y + z;
  }
}

class InitA10 {
  static void main() {
    var ma = new MultiAttr();
    ma.init(50, 60, 40);
    debugPrint(ma.sum());
  }
}