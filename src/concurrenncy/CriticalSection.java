package concurrenncy;

public class CriticalSection {
	protected int count = 0;

	public static void main(String[] args) {
		CriticalSection criticalSection = new CriticalSection();
		for (int i = 1; i <= 5; i++) {
			new Thread("" + i) {
				public void run() {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					criticalSection.add(Integer.parseInt(getName()));
				}
			}.start();
		}

	}

	void add(int value) {
		synchronized (this) {
			count += value;
			System.out.println("Count=" + count + ", Value=" + value + ", Thread :" + Thread.currentThread().getName());
		}
	}

}
