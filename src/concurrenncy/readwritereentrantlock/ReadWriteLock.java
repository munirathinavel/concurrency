package concurrenncy.readwritereentrantlock;

import java.util.HashMap;
import java.util.Map;

public class ReadWriteLock {

	private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();

	private int writeAccesses = 0;
	private int writeRequests = 0;
	private Thread writingThread = null;

	public synchronized void lockRead() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		while (!canGrantReadAccess(callingThread)) {
			wait();
		}

		readingThreads.put(callingThread, (getReadAccessCount(callingThread) + 1));
	}

	public synchronized void unlockRead() {
		Thread callingThread = Thread.currentThread();
		int accessCount = getReadAccessCount(callingThread);
		if (accessCount == 1) {
			readingThreads.remove(callingThread);
		} else {
			readingThreads.put(callingThread, (accessCount - 1));
		}
		notifyAll();
	}

	private boolean canGrantReadAccess(Thread callingThread) {
		if (writeAccesses > 0)
			return false;
		if (isReader(callingThread))
			return true;
		if (writeRequests > 0)
			return false;
		return true;
	}

	private int getReadAccessCount(Thread callingThread) {
		Integer accessCount = readingThreads.get(callingThread);
		if (accessCount == null)
			return 0;
		return accessCount.intValue();
	}

	private boolean isReader(Thread callingThread) {
		return readingThreads.get(callingThread) != null;
	}

	public synchronized void lockWrite() throws InterruptedException {
		writeRequests++;
		Thread callingThread = Thread.currentThread();
		while (!canGrantWriteAccess(callingThread)) {
			wait();
		}
		writeRequests--;
		writeAccesses++;
		writingThread = callingThread;
	}

	public synchronized void unlockWrite() throws InterruptedException {
		writeAccesses--;
		if (writeAccesses == 0) {
			writingThread = null;
		}
		notifyAll();
	}

	private boolean canGrantWriteAccess(Thread callingThread) {
		if (hasReaders())
			return false;
		if (writingThread == null)
			return true;
		if (!isWriter(callingThread))
			return false;
		return true;
	}

	private boolean hasReaders() {
		return readingThreads.size() > 0;
	}

	private boolean isWriter(Thread callingThread) {
		return writingThread == callingThread;
	}

}