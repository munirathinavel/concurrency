package concurrenncy.readwritelock;

public class Synchronizer {

	ReadWriteLock lock = new ReadWriteLock();
	int sum = 0;

	public void doSynchronized(int i) throws InterruptedException {
		lock.lockWrite();
		sum += i;
		// lock.lockWrite();
		// System.out.println(Thread.currentThread().getName() + ", Sum:" +
		// sum);
		// Critical section goes here. Do lot of operation here.
		lock.unlockWrite();

		lock.lockRead();
		// lock.lockRead();
		System.out.println(Thread.currentThread().getName() + ", Sum:" + sum);
		lock.unlockRead();
	}
}
