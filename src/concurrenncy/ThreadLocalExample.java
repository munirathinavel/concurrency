package concurrenncy;

public class ThreadLocalExample {

	public static class MyRunnable implements Runnable {
		private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

		@Override
		public void run() {
			threadLocal.set((int) (Math.random() * 1234));
			try {
				Thread.sleep(1233);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println(threadLocal.get() + " ; Thread:" + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {
		MyRunnable runnable = new MyRunnable();
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
