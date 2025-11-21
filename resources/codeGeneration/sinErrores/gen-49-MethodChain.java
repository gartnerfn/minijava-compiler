///99&exitosamente
class MethodChain {
  int value;

  void setValue(int v) {
    value = v;
  }

  int chainedCall() {
    return value + 1;
  }
}

class Init35 {
  static void main() {
    var mc = new MethodChain();
    mc.setValue(98);
    debugPrint(mc.chainedCall());
  }
}
