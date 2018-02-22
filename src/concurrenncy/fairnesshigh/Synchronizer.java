package concurrenncy.fairnesshigh;

public class Synchronizer {

	LockObject lock = new LockObject();

	public void doSynchronized() throws InterruptedException {
		lock.lock();
		Thread.sleep(2000);
		System.out.println(Thread.currentThread().getName());
		// Critical section goes here. Do lot of operation here.
		lock.unlock();
	}
}
