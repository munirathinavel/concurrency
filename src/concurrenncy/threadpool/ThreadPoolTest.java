package concurrenncy.threadpool;

public class ThreadPoolTest {

	public static void main(String[] args) {

		Runnable task = new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Running the task with " + Thread.currentThread());
			}
		};
		ThreadPool threadPool = new ThreadPool(2, 25);

		for (int i = 0; i < 25; i++) {
			try {
				threadPool.execute(task);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
