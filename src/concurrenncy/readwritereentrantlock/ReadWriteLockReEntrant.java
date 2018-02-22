package concurrenncy.readwritereentrantlock;

import java.util.HashMap;
import java.util.Map;

public class ReadWriteLockReEntrant {

	private Map<Thread, Integer> readingThreads = new HashMap<>();
	int writingCount = 0;
	Thread writingThread = null;
	int writingRequests = 0;

	public synchronized void lockRead() throws InterruptedException {
		Thread currentThread = Thread.currentThread();
		while (writingCount > 0 || writingRequests > 0 || readingThreads.get(currentThread) != null) {
			wait();
		}
		if (readingThreads.get(currentThread) > 0) {
			readingThreads.put(currentThread, readingThreads.get(currentThread) + 1);
		} else {
			readingThreads.put(currentThread, 1);
		}
	}

	public synchronized void unlockRead() {
		Thread currentThread = Thread.currentThread();
		if (readingThreads.get(currentThread) > 0) {
			readingThreads.put(currentThread, readingThreads.get(currentThread) - 1);
		} else {
			readingThreads.remove(currentThread);
		}
		notifyAll();
	}

	public synchronized void lockWrite() throws InterruptedException {
		Thread currentThread = Thread.currentThread();
		writingRequests++;
		while (readingThreads.size() > 0 || writingThread != null || writingThread != currentThread) {
			wait();
		}
		writingCount++;
		writingRequests--;
		writingThread = currentThread;
	}

	public synchronized void unlockWrite() {
		writingCount--;
		if (writingCount == 0) {
			writingThread = null;
		}
		notifyAll();
	}
}
