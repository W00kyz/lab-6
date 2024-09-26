import java.util.concurrent.BlockingQueue;

public class TaskProducer implements Runnable {

    BlockingQueue<Task> queue; 

    public TaskProducer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("TaskProducer");
        try {
            queue.put(new Task(0));
            System.out.println(queue.toString());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}