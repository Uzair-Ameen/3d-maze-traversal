

import java.util.Scanner;

public class Maze3d {
    private static final int ROOM = 0;
    private static final int WALL = 1;
    private static final int START = 2;
    private static final int EXIT = 3;

    private int[][][] maze;
    private boolean[][][] visited;
    private Node start;
    private Node end;
    private int depth, height, width;

    public Maze3d(int depth, int h, int w) {
        this.depth = depth;
        height = h;
        width = w;
        readFromConsole();
    }

    private void initializeMaze(String text) {

        String[] lines = text.split("[\r]?\n");
        maze = new int[depth][height][width];
        visited = new boolean[depth][height][width];
        for (int i = 0; i < getDepth(); i++) {
            for (int row = 0; row < getHeight(); row++) {
                for (int col = 0; col < getWidth(); col++) {
                    if (lines[row + i * height].charAt(col) == 'X')
                        maze[i][row][col] = WALL;
                    else if (lines[row + i * height].charAt(col) == 'S') {
                        maze[i][row][col] = START;
                        start = new Node(row, col, i);
                    } else if (lines[row + i * height].charAt(col) == 'E') {
                        maze[i][row][col] = EXIT;
                        end = new Node(row, col, i);
                    } else
                        maze[i][row][col] = ROOM;
                }
            }
        }
    }

    public void readFromConsole() {
        Scanner sc = new Scanner(System.in);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < height * depth; i++) {
            String inLine = sc.nextLine();
            if (inLine.length() < getWidth()) {
                System.out.println("Only rows of length " + getWidth() + " will be accepted. Try again");
                i--;
                continue;
            }
            if (inLine.trim().equals("") || inLine.trim().equals("\n")) {
                i--;
                continue;
            }
            s.append(inLine).append("\n");
        }
        initializeMaze(s.toString());
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public Node getEntry() {
        return start;
    }

    public Node getExit() {
        return end;
    }

    public boolean isStart(int x, int y, int z) {
        return x == start.getX() && y == start.getY() && z == start.getZ();
    }
    public boolean isExit(int x, int y, int z) {
        return x == end.getX() && y == end.getY() && z == end.getZ();
    }

    public boolean isVisited(int row, int col, int d) {
        return visited[d][row][col];
    }

    public boolean isWall(int row, int col, int d) {
        return maze[d][row][col] == WALL;
    }

    public void setVisited(int row, int col, int d) {
        visited[d][row][col] = true;
    }

    public boolean isValid(int row, int col, int d) {
        return row >= 0 && row < getHeight() && col >= 0 && col < getWidth() && d >= 0 && d < getDepth();
    }
}