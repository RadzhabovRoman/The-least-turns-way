import sun.misc.Queue;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Maze {


    private int[][] maze;//массив с лабиринтом
    private Point[][] prev;
    private Point startCell = new Point();//стартовая точка
    private Point finishCell = new Point();//конечная точка
    private Point[] direction = new Point[]{new Point(1,0),new Point(0,1),new Point(-1,0),new Point(0,-1)};//вниз,вправо,вверх,влево

    private void readFromFile(){//метод чтения из файла
        try {
            Scanner scanner = new Scanner(new File("in.txt"));
            int x = scanner.nextInt();
            scanner.nextLine();
            int y = scanner.nextInt();
            scanner.nextLine();
            maze = new int[x][y];
            prev = new Point[x][y];
            for (int row = 0; row < x; row++) {
                String line = scanner.nextLine();
                line = line.replaceAll("[^0,1]", "");
                for (int col = 0; col < line.length(); col++) {
                    maze[row][col] = Integer.parseInt(String.valueOf(line.charAt(col)));
                    if (maze[row][col]==0)
                        prev[row][col] = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
                    else maze[row][col]=-1;
                }
            }
            startCell.setLocation(scanner.nextInt()-1,scanner.nextInt()-1);
            //чтобы не было неудобств у юзера с координатами
            scanner.nextLine();
            finishCell.setLocation(scanner.nextInt()-1,scanner.nextInt()-1);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void widthSearch() throws InterruptedException {//просто обход в ширину(чекай)
         Queue queue = new Queue();
         //Point[][] prev = new Point[this.prev[0].length][this.prev.length];
         ArrayList<Point> visited = new ArrayList<>();
         queue.enqueue(startCell);
         while (!queue.isEmpty()){
             Point current = (Point) queue.dequeue();
             visited.add(current);
             if (current.equals(finishCell))break;
             for (Point step:direction){
                 Point next = pointSum(current,step);
                 if(maze[next.x][next.y]==0&&!visited.contains(next)){
                     queue.enqueue(next);
                     prev[next.x][next.y]=current;
                 }
             }
         }
     }

    private void marking() throws InterruptedException {
        sun.misc.Queue queue = new sun.misc.Queue<>();
        Point previous;
        maze[startCell.x][startCell.y] = 1;
        for (int i=0;i<4;i++){//обработать 1 фронт вокруг старта
            Point next = pointSum(startCell,direction[i]);
            if(maze[next.x][next.y]==0) {
                queue.enqueue(next);
                maze[next.x][next.y]=1;
                prev[next.x][next.y] = startCell;
            }
        }
        while (!queue.isEmpty()) {//строим новый фронт
            Point current = (Point) queue.dequeue();
            if (current.equals(finishCell)&&queue.isEmpty())//если последний элемент является финишным, то можно закончить
                return;
            previous = prev[current.x][current.y];
            for(int i = 0;i<direction.length;i++){
                Point next = pointSum(current,direction[i]);
                if (maze[next.x][next.y]==1)
                    continue;
                if (maze[next.x][next.y]==0 || maze[next.x][next.y]>maze[current.x][current.y]+step(previous,current,direction[i])){
                    //если количество изгибов в новом пути меньше чем в старом, либо это "первооткрыватель", то установить новые значения
                    queue.enqueue(next);
                    maze[next.x][next.y]=maze[current.x][current.y]+step(previous,current,direction[i]);
                    prev[next.x][next.y]=current;
                }
            }
        }
    }

    private void widthSearch() throws InterruptedException {//просто обход в ширину(для сравнения с моим алгоритмом)
        Queue queue = new Queue();
        //Point[][] prev = new Point[this.prev[0].length][this.prev.length];
        ArrayList<Point> visited = new ArrayList<>();
        queue.enqueue(startCell);
        while (!queue.isEmpty()){
            Point current = (Point) queue.dequeue();
            visited.add(current);
            if (current.equals(finishCell))break;
            for (Point step:direction){
                Point next = pointSum(current,step);
                if(maze[next.x][next.y]==0&&!visited.contains(next)){
                    queue.enqueue(next);
                    prev[next.x][next.y]=current;
                }
            }
        }
    }

    private int step(Point previous, Point current, Point direction){
        if (pointSum(previous,direction).equals(current))//если направление изменилось, то из предыдущей в настоящую не попасть
            return 0;                                    //а если не менялось, то все совпадет и прибавлять изгиб не надо
        else return 1;
    }

    private static Point pointSum(Point current, Point step){
        return new Point(current.x+step.x, current.y+step.y);
    }

    public void out(){//вывод для моего алгоритма
        Stack<Point> stack = new Stack<>();
        StringBuilder str = new StringBuilder();
        Point t = finishCell;
        if (!prev[t.x][t.y].equals(new Point(Integer.MAX_VALUE,Integer.MAX_VALUE))){//проверка на то что мы дошли до финиша
            str.append(startCell.x+1).append(",").append(startCell.y+1).append("\r\n");
            stack.push(t);//кладу в стэк путь до начала от конца
            while(!prev[t.x][t.y].equals(startCell)){
                t = prev[t.x][t.y];
                stack.push(t);
            }
            while (!stack.empty()){
                Point point = stack.pop();
                str.append(point.x+1).append(",").append(point.y+1).append("\r\n");//достаю путь из стэка
            }
        }
        else str.append("No way");
        try{
            FileWriter writer = new FileWriter (new File("out.txt"));
            writer.append(str);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void table(){
        for (int i = 0; i<maze.length;i++){
            for (int j=0; j<maze[0].length;j++){
                System.out.print(maze[i][j]+" ");
            }
            System.out.println();
        }

       /* for (int i = 0; i<prev.length;i++){
            for (int j=0; j<prev[0].length;j++){
                System.out.print(prev[i][j]+" ");
            }
            System.out.println();
        }*/
    }

    public static void main(String[] args) throws InterruptedException {
        Maze maze = new Maze();
        maze.readFromFile();
        maze.marking();//мой алгоритм
        //maze.widthSearch();//алгоритм с которым сравнить (если вместе запустить выдаст ошибку, т.к. файлы перезапишутся
        maze.table();
        maze.out();
        //сравнить с обычным поиском в ширину!!!!
    }
}
