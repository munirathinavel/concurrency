package concurrenncy.blockingQueue;

public class TestApp {

	public static void main(String[] args) {
		final BlockingQueueImpl blockingQueue = new BlockingQueueImpl(10);

		for (int i = 1; i <= 30; i++) {
			final int value = i;
			new Thread() {
				public void run() {
					try {
						blockingQueue.enqueue(value);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}

		for (int i = 1; i <= 30; i++) {
			new Thread() {
				public void run() {
					try {
						blockingQueue.dequeue();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}
	}

}
