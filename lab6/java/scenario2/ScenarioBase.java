import java.util.concurrent.*;



public class ScenarioBase {
    

    BlockingQueue<Task> queue;
    ScheduledExecutorService taskProducers;
    ScheduledExecutorService nodes;
    ExecutorService resultados;

    public ScenarioBase() {
        this.queue = new PriorityBlockingQueue<>();
        this.taskProducers = Executors.newScheduledThreadPool(3);
        this.nodes = Executors.newScheduledThreadPool(5);
        this.resultados = Executors.newSingleThreadExecutor();
    }

    public static void main(String[] args) {
        ScenarioBase scenario = new ScenarioBase();

        scenario.run();
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
           taskProducers.scheduleAtFixedRate(new TaskProducer(queue), 0, 1000, TimeUnit.MILLISECONDS);
        }

        for (int i = 0; i < 3; i++) {
            nodes.scheduleAtFixedRate(new Node(queue), 0, 5000, TimeUnit.MILLISECONDS);
        }

        
        resultados.submit(() -> {
            imprimirResultados();
        });


        // Fazer shutdown;
        Executors.newSingleThreadExecutor().submit(() -> {
            taskProducers.shutdown();
            nodes.shutdown();
            resultados.shutdown();
        });

    }

    public void imprimirResultados() {
        System.out.println("Resultados:");
        for (Task task : queue) {
            System.out.println("Task " + task.id);
        }
    }


}
