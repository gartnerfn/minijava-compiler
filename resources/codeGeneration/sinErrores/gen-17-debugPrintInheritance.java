///300&exitosamente

class Level1 {
  int level1Method() {
    return 100;
  }
}

class Level2 extends Level1 {
  int level2Method() {
    return level1Method() + 100;
  }
}

class Level3 extends Level2 {
  int level3Method() {
    return level2Method() + 100;
  }
}

class Init {
  static void main() {
    var l3 = new Level3();
    debugPrint(l3.level3Method());
  }
}
