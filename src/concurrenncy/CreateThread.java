package concurrenncy;
class MyRunnableThread implements Runnable {
	@Override
	public void run() {
		System.out.println("Inside MyRunnableThread: " + Thread.currentThread().getName());
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {
		System.out.println("Inside MyThread1: " + Thread.currentThread().getName());
	}
}

public class CreateThread {

	static class MyRunnableThread1 implements Runnable {
		@Override
		public void run() {
			System.out.println("Inside MyRunnableThread1: " + Thread.currentThread().getName());
		}
	}

	public static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("Inside MyThread: " + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {

		System.out.println(Thread.currentThread().getName());

		Thread thread = new MyThread();
		thread.start();

		Thread t1 = new MyThread1();
		t1.start();

		Thread t2 = new Thread("MyThread-2") {
			@Override
			public void run() {
				System.out.println("Inside MyThread2: " + Thread.currentThread().getName());
			}
		};
		t2.start();
		System.out.println("Thread executed successfully..");

		Thread myRunnableThread = new Thread(new MyRunnableThread());
		myRunnableThread.start();

		Thread myRunnableThread1 = new Thread(new MyRunnableThread1());
		myRunnableThread1.start();

		Thread myRunnableThread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Inside MyThread2: " + Thread.currentThread().getName());
			}
		}, "myRunnableThread2");
		myRunnableThread2.start();

		System.out.println("Runnable Threads executed successfully..");

		for (int i = 0; i < 10; i++) {
			new Thread("Thread" + i) {
				public void run() {
					System.out.println("Thread:" + getName() + " running..");
				}
			}.start();
		}

	}
}
