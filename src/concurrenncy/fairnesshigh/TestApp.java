package concurrenncy.fairnesshigh;

public class TestApp {

	public static void main(String[] args) {
		final Synchronizer synchronizer = new Synchronizer();

		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					try {
						synchronizer.doSynchronized();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}
	}

}
