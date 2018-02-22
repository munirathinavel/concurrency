package concurrenncy.blockingQueue;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueueImpl {
	private List<Object> queue = new LinkedList<>();
	private int limit = 10;

	public BlockingQueueImpl(int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(Object item) throws InterruptedException {
		while (queue.size() == limit) {
			System.out.println("Queue is Full..so waiting to add new item..");
			wait();
		}
		if (queue.size() == 0) {
			notifyAll();
		}
		System.out.println(item + " added to Blocking Queue");
		queue.add(item);
	}

	public synchronized Object dequeue() throws InterruptedException {

		while (queue.size() == 0) {
			System.out.println("Queue is Empty..so waiting to remove an item..");
			wait();
		}

		if (queue.size() == limit) {
			notifyAll();
		}

		Object item = queue.remove(0);
		System.out.println(item + " removed from Blocking Queue");
		return item;
	}

}
