

public class Node {
    int x;
    int y;
    int z;
    Node parent;


    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Node(int x, int y, int z, Node parent) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.parent = parent;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getZ() {return z;}

    Node getParent() {
        return parent;
    }
}
