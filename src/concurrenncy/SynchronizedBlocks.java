package concurrenncy;

class Synchronization {
	int sum = 0;
	static int staticSum = 0;

	// Synchronized Instance Methods
	// One thread per instance
	public synchronized void add(int i) {
		sum += i;
		// System.out.println(sum);
	}

	// Synchronized Blocks in Instance Methods
	// One thread per instance
	public void addSyncBlock(int i) {
		synchronized (this) {
			sum += i;
			// System.out.println(sum);
		}
	}

	// Synchronized Static Methods
	// Allows one thread per JVM (Class level static method)
	public synchronized static void staticAdd(int i) {
		staticSum += i;
		System.out.println("StaticSum: " + staticSum);
	}

	// Synchronized Static Methods Block
	// Allows one thread per JVM (Class level static method)
	public static void staticBlockAdd(int i) {
		synchronized (Synchronization.class) {
			staticSum += i;
			System.out.println("StaticBlockSum: " + staticSum);
		}
	}

}

public class SynchronizedBlocks {

	public static void main(String[] args) {
		Synchronization synchronization = new Synchronization();
		for (int i = 1; i <= 10; i++) {
			final int value = i;
			new Thread(i + "") {
				public void run() {
					try {
						Thread.sleep(200);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					// Allows One thread
					synchronization.add(value);
					// Allows One Thread per JVM
					// synchronization.staticAdd(value);
					// Allows one thread per instance
					synchronization.addSyncBlock(value);
				}
			}.start();
		}

		for (int i = 1; i <= 10; i++) {
			final int value = i;
			new Thread(i + "") {
				public void run() {
					try {
						Thread.sleep(200);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					// Though new objects created everytime, this static method
					// doesn't let more than one thread get into as it's class
					// level static method
					Synchronization.staticAdd(value);
					Synchronization.staticBlockAdd(value);

				}
			}.start();
		}
	}

}
