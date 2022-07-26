public class ExampleSynchronized {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        ExampleSynchronized exampleSynchronized = new ExampleSynchronized();
        exampleSynchronized.doWork();
    }

    // метод синхронизации для исключения "гонки" потоков
    public synchronized void increment() {
        counter++;
    }

    public void doWork() throws InterruptedException {
        // поток 1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50505; i++) {
                    increment();
                }
            }
        });
        // поток 2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50505; i++) {
                    increment();
                }
            }
        });
        // запуск потоков
        thread1.start();
        thread2.start();
        // блокировка дальнейшего выполнения метода пока не завершатся потоки
        thread1.join();
        thread2.join();

        System.out.println(counter);
    }
}
