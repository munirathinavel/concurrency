package concurrenncy.fairness;

public class LockObject {

	private boolean isLocked = false;
	private Thread lockingThread = null;

	public synchronized void lock() throws InterruptedException {
		while (isLocked) {
			wait();
		}
		isLocked = true;
		lockingThread = Thread.currentThread();
	}

	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException("Current thread hasn't locked this Object");
		}
		isLocked = false;
		lockingThread = null;
		notify();
	}

}
