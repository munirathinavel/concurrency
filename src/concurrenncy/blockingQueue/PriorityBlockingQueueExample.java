package concurrenncy.blockingQueue;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class PriorityBlockingQueueProducer implements Runnable {
	BlockingQueue<String> priorityBlockingQueue = null;

	public PriorityBlockingQueueProducer(BlockingQueue<String> blockingQueue) {
		this.priorityBlockingQueue = blockingQueue;
	}

	public void run() {
		try {
			priorityBlockingQueue.put(UUID.randomUUID().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class PriorityBlockingQueueConsumer implements Runnable {
	BlockingQueue<String> priorityBlockingQueue = null;

	public PriorityBlockingQueueConsumer(BlockingQueue<String> blockingQueue) {
		this.priorityBlockingQueue = blockingQueue;
	}

	public void run() {
		try {
			System.out.println(priorityBlockingQueue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class PriorityBlockingQueueExample {

	public static void main(String[] args) {
		PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();

		PriorityBlockingQueueConsumer consumer = new PriorityBlockingQueueConsumer(priorityBlockingQueue);
		PriorityBlockingQueueProducer producer = new PriorityBlockingQueueProducer(priorityBlockingQueue);

		new Thread(producer).start();
		new Thread(consumer).start();

	}

}
