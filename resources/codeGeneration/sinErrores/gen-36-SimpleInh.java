///100&exitosamente
class Parent {
  int getValue() {
    return 100;
  }
}

class Child extends Parent {
}

class Init17 {
  static void main() {
    var child = new Child();
    debugPrint(child.getValue());
  }
}
