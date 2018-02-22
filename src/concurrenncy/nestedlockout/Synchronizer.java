package concurrenncy.nestedlockout;

public class Synchronizer {

	// LockObject lock = new LockObject();
	// SimpleLock lock = new SimpleLock();

	FairLockOut lock = new FairLockOut();

	public void doSynchronized() throws InterruptedException {
		System.out.println("Starting Thread Name:" + Thread.currentThread().getName());
		lock.lock();
		Thread.sleep(2000);
		System.out.println(Thread.currentThread().getName());
		// Critical section goes here. Do lot of operation here.
		lock.unlock();
	}
}
