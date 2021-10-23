import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private static final int[][] NEIGHBOURS = {{ 0, 1, 0 }, { 1, 0, 0 }, {0, 0, 1}, { 0, -1, 0 }, { -1, 0, 0 }, {0, 0, -1}};

    public List<Node> solve(Maze3d maze) {
        LinkedList<Node> nextInQueue = new LinkedList<>();
        Node start = maze.getEntry();
        nextInQueue.add(start);

        while (!nextInQueue.isEmpty()) {
            Node cur = nextInQueue.remove();
            if (!maze.isValid(cur.getX(), cur.getY(), cur.getZ()) || maze.isVisited(cur.getX(), cur.getY(), cur.getZ())) {
                continue;
            }
            if (maze.isWall(cur.getX(), cur.getY(), cur.getZ())) {
                maze.setVisited(cur.getX(), cur.getY(),cur.getZ());
                continue;
            }

            if (maze.isExit(cur.getX(), cur.getY(), cur.getZ())) {
                return compileRoute(cur);
            }

            for (int[] neighbor : NEIGHBOURS) {
                Node coordinate = new Node(cur.getX() + neighbor[0],
                        cur.getY() + neighbor[1], cur.getZ() + neighbor[2], cur);
                nextInQueue.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), cur.getZ());
            }
        }
        return Collections.emptyList();
    }

    private List<Node> compileRoute(Node current) {
        List<Node> route = new ArrayList<>();
        Node c = current;
        while (c != null) {
            route.add(c);
            c = c.parent;
        }
        return route;
    }
}