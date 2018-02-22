package concurrenncy.threadpool;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

	List<PoolThread> threads = new LinkedList<>();
	boolean isStopped = false;
	BlockingQueue blockingQueue = null;

	ThreadPool(int noOfThreads, int maxTasks) {
		blockingQueue = new BlockingQueue(maxTasks);

		for (int i = 0; i < noOfThreads; i++) {
			threads.add(new PoolThread(blockingQueue));
		}

		for (PoolThread poolThread : threads) {
			poolThread.start();
		}
	}

	public synchronized void execute(Runnable task) throws InterruptedException {
		if (isStopped) {
			throw new IllegalStateException("ThreadPool is stopped");
		}

		blockingQueue.enqueue(task);
	}

	public synchronized void stop() {
		isStopped = true;
		for (PoolThread poolThread : threads) {
			poolThread.doStop();
		}
	}

}
