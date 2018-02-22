package concurrenncy.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {
	private java.util.concurrent.BlockingQueue<Integer> blockingQueue = null;

	Producer(java.util.concurrent.BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			blockingQueue.put(1);
			Thread.sleep(1000);
			blockingQueue.put(2);
			Thread.sleep(1000);
			blockingQueue.put(3);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class Consumer implements Runnable {
	private java.util.concurrent.BlockingQueue<Integer> blockingQueue = null;

	Consumer(java.util.concurrent.BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
			System.out.println(blockingQueue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

public class ProducerConsumerBlockingQueue {

	public static void main(String[] args) {
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(100);
		Producer producer = new Producer(blockingQueue);
		Consumer consumer = new Consumer(blockingQueue);

		new Thread(producer).start();
		new Thread(consumer).start();

	}

}
