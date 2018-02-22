package concurrenncy;

class MonitorObject {

}

public class WaitAndNotify {
	MonitorObject monitorObject = new MonitorObject();
	boolean wasSignaled = false;

	public void doWait() {
		synchronized (monitorObject) {
			while (!wasSignaled) {
				try {
					System.out.println("Waiting");
					monitorObject.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			wasSignaled = false;
		}
	}

	public void doNofity() {
		synchronized (monitorObject) {
			wasSignaled = true;
			System.out.println("Notifying..");
			monitorObject.notify();

		}
	}

	public static void main(String[] args) {
		WaitAndNotify waitAndNotify = new WaitAndNotify();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				System.out.println("stating t1..");
				waitAndNotify.doNofity();
				System.out.println("t1 completed. \nt1 notifies t2...");
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				System.out.println("t2 waiting..");
				waitAndNotify.doWait();
				System.out.println("t2 completed.");
			}
		};
		t1.start();
		t2.start();

	}
}
