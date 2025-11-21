///45&exitosamente
class MultiParam {
  int process(int a, int b, int c, int d) {
    return a + b + c + d;
  }
}

class InitA9 {
  static void main() {
    var mp = new MultiParam();
    debugPrint(mp.process(10, 15, 10, 10));
  }
}
