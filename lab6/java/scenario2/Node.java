import java.util.concurrent.BlockingQueue;

public class Node implements Runnable {


    BlockingQueue<Task> queue; 

    public Node(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
      Task task;
      try {
        task = queue.take();
        System.out.println("Node" + task.id);
        task.execute();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
}
