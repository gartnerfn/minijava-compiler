///15&exitosamente
class WithConstructor {
  int value;

  void init(int v) {
    value = v;
  }

  int getValue() {
    return value;
  }
}

class Init27 {
  static void main() {
    var wc = new WithConstructor();
    wc.init(15);
    debugPrint(wc.getValue());
  }
}
