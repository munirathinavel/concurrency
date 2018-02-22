package concurrenncy.threadpool;

public class PoolThread extends Thread {

	BlockingQueue blockingQueue = null;
	boolean isStopped = false;

	PoolThread(BlockingQueue blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void run() {
		while (!isStopped()) {
			try {
				Runnable runnable = (Runnable) blockingQueue.dequeue();
				runnable.run();
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public void doStop() {
		isStopped = true;
		this.interrupt();
	}

	public synchronized boolean isStopped() {
		return isStopped;
	}
}
