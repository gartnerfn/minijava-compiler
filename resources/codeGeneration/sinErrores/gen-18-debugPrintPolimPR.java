///250&exitosamente
class Animal {
  int getID() {
    return 100;
  }
}

class Dog extends Animal {
  int getID() {
    return 250;
  }
}

class InitA8 {
  static void main() {
    var d = new Dog();
    debugPrint(d.getID());
  }
}
