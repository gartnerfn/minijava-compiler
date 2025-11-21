///100&exitosamente
class Counter {
  int count;

  void setCount(int c) {
    count = c;
  }

  int getCount() {
    return count;
  }
}

class Init8 {
  static void main() {
    var ctr = new Counter();
    ctr.setCount(100);
    debugPrint(ctr.getCount());
  }
}