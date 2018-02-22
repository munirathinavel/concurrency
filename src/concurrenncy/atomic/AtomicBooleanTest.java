package concurrenncy.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest {

	public static void main(String[] args) {

		Thread t = new Thread() {
			public void run() {
				System.out.println("Name" + Thread.currentThread().getName());
			}
		};
		t.start();
//		t.start(); // java.lang.IllegalThreadStateException
		/*for (int i = 0; i < 10; i++) {
			t.start();
		}*/
	}

}

class Lock {
	AtomicBoolean locked = new AtomicBoolean(false);

	public void lock() {
		locked.compareAndSet(false, true);
	}
}
