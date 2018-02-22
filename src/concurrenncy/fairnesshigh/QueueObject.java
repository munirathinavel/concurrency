package concurrenncy.fairnesshigh;

public class QueueObject {

	boolean isNotified = false;

	public synchronized void doWait() throws InterruptedException {
		while (!isNotified) {
			this.wait();
		}
		this.isNotified = false;
	}

	public synchronized void doNotify() {
		isNotified = true;
		this.notify();
	}

	public boolean equals(Object o) {
		return this == o;
	}
}
