package concurrenncy.threadpool;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {
	List<Object> queue = new LinkedList<>();
	int limit = 10;

	BlockingQueue(int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(Object task) throws InterruptedException {
		while (queue.size() == limit) {
			wait();
		}
		if (queue.size() == 0) {
			notifyAll();
		}
		this.queue.add(task);
	}

	public synchronized Object dequeue() throws InterruptedException {
		while (this.queue.size() == 0) {
			wait();
		}
		if (queue.size() == limit) {
			notifyAll();
		}
		return queue.remove(0);
	}
}
