package concurrenncy.blockingQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {

	public static void main(String[] args) throws InterruptedException {

		// Unbounded - Max No. of Values = Integer.MAXVALUE
		LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

		LinkedBlockingQueue<String> linkedBlockingQueueBuonded = new LinkedBlockingQueue<>(100);

		for (int i = 0; i < 10000; i++) {
			final int value = i;
			new Thread() {
				public void run() {
					try {
						Thread.sleep(900);
						System.out.println("put:" + value);
						linkedBlockingQueue.put(value + "");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
			
			new Thread() {
				public void run() {
					try {
						Thread.sleep(1000);
						System.out.println("take:" + linkedBlockingQueue.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}

		// ExecutorService executor = Executors.newCachedThreadPool();

		// executor.submit(()->{linkedBlockingQueue.put(Thread.currentThread().getName())});
		// linkedBlockingQueue.put(123);

		System.out.println(linkedBlockingQueue.take());

	}
}
