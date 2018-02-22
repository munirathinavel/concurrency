package concurrenncy.nonblockingalogirithms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class NonBlockingPerformance {

	public static void main(String[] args) {
		int nuomberOfThreads = 100;
		int n = 10000000;
		SynchronizedCounter counter = new SynchronizedCounter();
		AtomicCounter atomicCounter = new AtomicCounter();
		ExecutorService executor = Executors.newFixedThreadPool(nuomberOfThreads);
		Runnable increment = new Runnable() {
			public void run() {
				counter.inc();
			}
		};

		Runnable atomicIncrement = new Runnable() {
			public void run() {
				atomicCounter.inc();
			}
		};
		long currentTime = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			executor.submit(increment);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {

		}
		System.out.println(counter.count());
		System.out.println("Time taken:" + (System.currentTimeMillis() - currentTime));

		executor = Executors.newFixedThreadPool(nuomberOfThreads);
		currentTime = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			executor.submit(atomicIncrement);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {

		}
		System.out.println(atomicCounter.count());
		System.out.println("Time taken:" + (System.currentTimeMillis() - currentTime));
	}
}

class SynchronizedCounter {
	long count = 0;

	public void inc() {
		synchronized (this) {
			count++;
		}
	}

	public long count() {
		synchronized (this) {
			return this.count;
		}
	}
}

class AtomicCounter {
	private AtomicLong count = new AtomicLong(0);

	public void inc() {
		boolean updated = false;
		while (!updated) {
			long prevCount = this.count.get();
			updated = this.count.compareAndSet(prevCount, prevCount + 1);
		}
	}

	public long count() {
		return this.count.get();
	}
}