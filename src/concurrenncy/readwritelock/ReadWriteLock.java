package concurrenncy.readwritelock;

public class ReadWriteLock {

	int readers = 0;
	int writers = 0;
	int writingRequests = 0;

	public synchronized void lockRead() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " Requesting Read Lock");
		while (writers > 0 || writingRequests > 0) {
			wait();
			System.out.println(Thread.currentThread().getName() + " waiting in ReadLock");
		}
		readers++;
		System.out.println(Thread.currentThread().getName() + " releases in read Lock");
	}

	public synchronized void unlockRead() {
		System.out.println(Thread.currentThread().getName() + " entering Read Unlock");
		readers--;
		notifyAll();
		System.out.println(Thread.currentThread().getName() + " notifying Read Unlock");
	}

	public synchronized void lockWrite() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " Requesting Write Lock");
		writingRequests++;
		while (readers > 0 || writers > 0) {
			wait();
			System.out.println(Thread.currentThread().getName() + " waiting in write Lock");
		}
		writers++;
		writingRequests--;
		System.out.println(Thread.currentThread().getName() + " releases Write Lock");
	}

	public synchronized void unlockWrite() {
		System.out.println(Thread.currentThread().getName() + " entering Write Unlock");
		writers--;
		notifyAll();
		System.out.println(Thread.currentThread().getName() + " notifying Write Unlock");
	}
}
