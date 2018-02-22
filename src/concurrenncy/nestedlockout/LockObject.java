package concurrenncy.nestedlockout;

import java.util.ArrayList;
import java.util.List;

public class LockObject {

	private boolean isLocked = false;
	private Thread lockingThread = null;
	private List<QueueObject> waitingThreads = new ArrayList<>();

	public void lock() throws InterruptedException {
		QueueObject queueObject = new QueueObject();
		boolean isLockedForThisThread = false;
		synchronized (this) {
			waitingThreads.add(queueObject);

			while (isLockedForThisThread) {
				synchronized (this) {
					isLockedForThisThread = isLocked || queueObject != waitingThreads.get(0);
					if (!isLockedForThisThread) {
						isLocked = true;
						lockingThread = Thread.currentThread();
						waitingThreads.remove(queueObject);
						return;
					}
				}
				synchronized (queueObject) {
					try {
						queueObject.doWait();
					} catch (Exception e) {
						synchronized (this) {
							waitingThreads.remove(queueObject);
							throw e;
						}
					}
				}
			}
		}

	}

	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			// System.out.println("Current thread hasn't locked this Object");
			return;
		}
		isLocked = false;
		lockingThread = null;
		if (waitingThreads.size() > 0) {
			waitingThreads.get(0).doNotify();
		}
	}

}
