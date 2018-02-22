package concurrenncy.nestedlockout;

import java.util.ArrayList;
import java.util.List;

public class FairLockOut {

	private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();
	private boolean isLocked = false;
	private Thread lockingThread = null;

	public void lock() {
		QueueObject queueObject = new QueueObject();
		synchronized (this) {
			waitingThreads.add(queueObject);
			while (isLocked && lockingThread != Thread.currentThread()) {
				synchronized (queueObject) {
					try {
						queueObject.wait();
					} catch (InterruptedException e) {
						System.out.println(e);
					}
				}
			}
			waitingThreads.remove(queueObject);
			isLocked = true;
			lockingThread = Thread.currentThread();
		}
	}

	public synchronized void unlock() {
		if (lockingThread != Thread.currentThread()) {
			throw new IllegalStateException("Wrong Thread calling unlock method..");
		}
		isLocked = false;
		lockingThread = null;
		if (waitingThreads.size() > 0) {
			QueueObject queueObject = waitingThreads.get(0);
			synchronized (queueObject) {
				queueObject.notify();
			}
		}
	}
}
