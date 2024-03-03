/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_cwk;

/**
 *
 * @author Shivangi Shah
 * Student id: w1747361
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class QItem {

    int row;
    int col;
    int dis;

    public QItem(int r, int c, int dis) {
        this.row = r;
        this.col = c;
        this.dis = dis;
    }
}

public class Maze_Solver {

    int size;
    int startRow = 0, startCol = 0;
    static ArrayList<ArrayList<Character>> matrix = new ArrayList<>();

    public void addEdgesFromFile() throws FileNotFoundException {
        // read in the data
        Scanner input = new Scanner(new File("maze30_1.txt"));
        while (input.hasNextLine()) {
            ///Character line = (Character)input.next(); 
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList<Character> col = new ArrayList<>();
            while (colReader.hasNextLine()) {
                String temp = colReader.nextLine();
                for (int i = 0; i < temp.length(); i++) {
                    col.add(temp.charAt(i));
                }
                matrix.add(col);
            }
        }
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).size(); j++) {
                if (matrix.get(i).get(j) == 'S') {
                    //strores the co-ordinate for character S
                    startRow = i;
                    startCol = j;
                }
            }
        }
    }

    //this prints the whole maze with commas
    public static void printList() {
        for (ArrayList<Character> arr : matrix) {
            System.out.println(arr);
        }
    }

    //this is validity check for the maze 
    private static boolean isValid(int x, int y, boolean[][] visited) {
        if (x >= 0 && y >= 0 && x < matrix.size() && y < matrix.get(0).size() && matrix.get(x).get(y) != '0' && visited[x][y] == false) {
            return true;
        }
        return false;
    }

    //this prints the movement of the race
    public void printPath(ArrayList<QItem> path, QItem start) {
        QItem p = start;
        for (int i = 0; i <= path.size() - 1; i++) {

            QItem q = path.get(i);

            //The index is calculated from 0
            //So the 1st point in the maze is (0,0)
            
            if (p.row - q.row >= 1 && p.col - q.col == 0) {
                System.out.println("Move up to: " + "(" + q.row + "," + q.col + ")");
            }

            if (p.row - q.row <= -1 && p.col - q.col == 0) {
                System.out.println("Move down to: " + "(" + q.row + "," + q.col + ")");
            }
            if (p.col - q.col >= 1) {
                System.out.println("Move left to: " + "(" + q.row + "," + q.col + ")");
            }
            if (p.col - q.col <= -1) {
                System.out.println("Move right to: " + "(" + q.row + "," + q.col + ")");
            }
            p = q;

        }
    }

    //finds the shortest path for reaching the co-ordinates of Character F from co-ordinates of character 'S'
    public int BFS() {
        int x = 0;
        int y = 0;
        Queue<ArrayList<QItem>> queue = new LinkedList<>();
        ArrayList<QItem> path = new ArrayList<>();
        path.add(new QItem(startRow, startCol, 0));
        queue.add(path);
        boolean[][] visited = new boolean[matrix.size()][matrix.get(0).size()];

        while (!queue.isEmpty()) {
            path = queue.remove();
            QItem p = path.get(path.size() - 1);

            // Destination found;
            if (matrix.get(p.row).get(p.col) == 'F') {
                printPath(path, new QItem(startRow, startCol, 0));
                System.out.println("End: " + "(" + p.row + "," + p.col + ")");
                System.out.println("Done!");
                visited[p.row + 1][p.row] = true;
                System.out.print("Number of steps: ");
                return p.dis;

            }

            // moving up
            if (isValid(p.row - 1, p.col, visited)) {
                ArrayList<QItem> newPath = new ArrayList<>();
                newPath.addAll(path);
                QItem nextVal = new QItem(p.row - 1, p.col, p.dis + 1);
                newPath.add(nextVal);
                while ((matrix.get(nextVal.row).get(nextVal.col) != '0') && x < matrix.size() && y < matrix.get(0).size()) {
                    if (nextVal.row - 1 < 0) {
                        break;
                    }

                    if (matrix.get(nextVal.row).get(nextVal.col) == 'F') {
                        break;
                    } else {
                        nextVal.row--;
                    }
                }
                if ((matrix.get(nextVal.row).get(nextVal.col) == '0')) {
                    nextVal.row++;
                }
                if (visited[nextVal.row][nextVal.col] == false) {
                    queue.add(newPath);
                    visited[nextVal.row][nextVal.col] = true;
                }

            }

            // moving down
            if (isValid(p.row + 1, p.col, visited)) {
                ArrayList<QItem> newPath = new ArrayList<>();
                newPath.addAll(path);
                QItem nextVal = new QItem(p.row + 1, p.col, p.dis + 1);
                newPath.add(nextVal);
                while ((matrix.get(nextVal.row).get(nextVal.col) != '0') && x < matrix.size() && y < matrix.get(0).size()) {
                    if (nextVal.row + 1 > matrix.size() - 1) {
                        break;
                    }

                    if (matrix.get(nextVal.row).get(nextVal.col) == 'F') {
                        break;
                    } else {
                        nextVal.row++;
                    }
                }
                if ((matrix.get(nextVal.row).get(nextVal.col) == '0')) {
                    nextVal.row--;
                }
                if (visited[nextVal.row][nextVal.col] == false) {
                    queue.add(newPath);
                    visited[nextVal.row][nextVal.col] = true;
                }
            }

            // moving left
            if (isValid(p.row, p.col - 1, visited)) {
                ArrayList<QItem> newPath = new ArrayList<>();
                newPath.addAll(path);
                QItem nextVal = new QItem(p.row, p.col - 1, p.dis + 1);
                newPath.add(nextVal);
                while ((matrix.get(nextVal.row).get(nextVal.col) != '0') && x < matrix.size() && y < matrix.get(0).size()) {
                    if (nextVal.col - 1 < 0) {
                        break;
                    }

                    if (matrix.get(nextVal.row).get(nextVal.col) == 'F') {
                        break;
                    } else {
                        nextVal.col--;
                    }
                }
                if ((matrix.get(nextVal.row).get(nextVal.col) == '0')) {
                    nextVal.col++;
                }
                if (visited[nextVal.row][nextVal.col] == false) {
                    queue.add(newPath);
                    visited[nextVal.row][nextVal.col] = true;
                }
            }

            // moving right
            if (isValid(p.row, p.col + 1, visited)) {
                ArrayList<QItem> newPath = new ArrayList<>();
                newPath.addAll(path);
                QItem nextVal = new QItem(p.row, p.col + 1, p.dis + 1);
                newPath.add(nextVal);
                while ((matrix.get(nextVal.row).get(nextVal.col) != '0') && x < matrix.size() && y < matrix.get(0).size()) {
                    if (nextVal.col + 1 > matrix.get(0).size() - 1) {
                        break;
                    }

                    if (matrix.get(nextVal.row).get(nextVal.col) == 'F') {
                        break;
                    } else {
                        nextVal.col++;
                    }
                }
                if ((matrix.get(nextVal.row).get(nextVal.col) == '0')) {
                    nextVal.col--;
                }
                if (visited[nextVal.row][nextVal.col] == false) {
                    queue.add(newPath);
                    visited[nextVal.row][nextVal.col] = true;
                }

            }
        }
        System.out.println("There is not solution to this maze");
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Maze_Solver bfs = new Maze_Solver();
        bfs.addEdgesFromFile();
        //this method printList(), prints the whole maze with commas, but comment this out to decrease runtime
        printList();
        System.out.println("Start at: (" + bfs.startRow + (",") + bfs.startCol + (")"));
        System.out.println(bfs.BFS());
    }

}
