package concurrenncy.readwritelock;

public class TestApp {

	public static void main(String[] args) {
		final Synchronizer synchronizer = new Synchronizer();

		for (int i = 1; i <= 10; i++) {
			final int value = i;
			new Thread() {
				public void run() {
					try {
						synchronizer.doSynchronized(value);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}
	}

}
