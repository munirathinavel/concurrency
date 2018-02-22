package concurrenncy.nestedlockout;

public class SimpleLock {
	private QueueObject queueObject = new QueueObject();
	private boolean isLocked = false;

	public void lock() throws InterruptedException {
		synchronized (this) {	
			while (isLocked) {
				synchronized (queueObject) {
					queueObject.wait();
				}
			}
			isLocked = true;
		}
	}

	public void unlock() {
		synchronized (this) {
			isLocked = false;
			synchronized (queueObject) {
				queueObject.notify();
			}
		}
	}

}
