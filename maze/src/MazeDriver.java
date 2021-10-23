
import java.util.List;
import java.util.Scanner;

public class MazeDriver {
    private static List<Node> path;
    public static void main(String[] args)  {
        int input = 0;
        int h, w, depth;
        while (input != 5) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Width: ");
            w = scan.nextInt();
            System.out.println("Enter Height: ");
            h = scan.nextInt();
            System.out.println("Enter Depth: ");
            depth = scan.nextInt();
            System.out.println("Enter maze below. Only rows of width 8 will be accepted.");
            Maze3d maze = new Maze3d(depth, h, w);
            int c_t = 1;
            while (c_t == 1) {
                c_t = 0;
                System.out.println("1. Solve suboptimally");
                System.out.println("2. Estimate optimal solution cost");
                System.out.println("3. Solve optimally");
                System.out.println("4. Enter new puzzle");
                System.out.println("5. Quit");
                System.out.print(":> ");
                input = scan.nextInt();
                System.out.println();
                switch (input) {
                    case 1 -> {
                        System.out.println("Not Implemented. Use optimal instead\n");
                        c_t = 1;
                    }
                    case 3 -> {
                        bfs(maze);
                        printPath();
                    }
                    case 2 -> {
                        bfs(maze);
                        System.out.println("Optimal Solution Cost:\t" + getCost());
                    }

                    case 5 -> System.out.println("Goodbye!");
                }
            }

        }
    }

    private static int getCost() {
        return path.size() - 1;
    }
    private static void bfs(Maze3d maze) {
        Solver bfs = new Solver();
        path = bfs.solve(maze);
    }

    private static char getDir(Node c1, Node c2) {
        if (c1.getY() < c2.getY()) return 'E';
        if (c1.getY() > c2.getY()) return 'W';
        if (c1.getX() < c2.getX()) return 'S';
        if (c1.getX() > c2.getX()) return 'N';
        if (c1.getZ() < c2.getZ()) return 'D';
        return 'U';
    }

    private static void printPath() {
        System.out.println("Finding Solution....");
        if (path.isEmpty())
            System.out.println("Exit not reachable");
        System.out.println("Optimal Path Cost\t: \n" + (path.size() - 1));
        System.out.println("Optimal Path: ");
        Node[] arr = path.toArray(new Node[0]);
        for (int i = arr.length - 1; i > 0; i--)
            System.out.print(getDir(arr[i], arr[i - 1]) + " ");
        System.out.println();
    }
}