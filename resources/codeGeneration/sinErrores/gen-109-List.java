///1&2&3&exitosamente
class Test79 {
    static void main() {
        var n1 = new Node(1);
        var n2 = new Node(2);
        var n3 = new Node(3);
        n1.next = n2;
        n2.next = n3;

        var current = n1;
        while (current != null) {
            System.printIln(current.value);
            current = current.next;
        }
    }
}
class Node {
    public int value;
    public Node next;
    Node(int v) {
        value = v;
        next = null;
    }
}