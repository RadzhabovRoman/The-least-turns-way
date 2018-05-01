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
