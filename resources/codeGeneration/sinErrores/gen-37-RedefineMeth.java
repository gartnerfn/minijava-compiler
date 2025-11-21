///200&exitosamente
class Base {
  int getValue() {
    return 100;
  }
}

class Derived extends Base {
  int getValue() {
    return 200;
  }
}

class Init18 {
  static void main() {
    var d = new Derived();
    debugPrint(d.getValue());
  }
}
