///150&exitosamente
class Test78 {
    static void main() {
        var rect = new Rectangle(10, 15);
        System.printIln(rect.area());
    }
}
class Rectangle {
    public int width;
    public int height;
    Rectangle(int w, int h) {
        width = w;
        height = h;
    }
    int area() {
        return width * height;
    }
}