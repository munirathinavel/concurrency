package concurrency.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {

	public static void main(String[] args) {

		concurrentHashMap();
		hashMap();

	}

	private static void hashMap() {
		long startTime = System.currentTimeMillis();
		Map<Integer, String> hashMap = new HashMap<>();
		Runnable producer = new Runnable() {
			public void run() {
				System.out.println("HashMap:Write:" + Thread.currentThread().getName());
				hashMap.put((int) Math.random(), Thread.currentThread().getName());
			}
		};
		Runnable consumer = new Runnable() {
			public void run() {
				System.out.println("HashMap:Read:" + Thread.currentThread().getName());
			}
		};

		for (int i = 0; i < 10000; i++) {
			new Thread(producer).start();
			new Thread(consumer).start();
		}
		System.out.println(System.currentTimeMillis() - startTime);
	}

	private static void concurrentHashMap() {
		long startTime = System.currentTimeMillis();
		Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
		Runnable producer = new Runnable() {
			public void run() {
				System.out.println("Write:" + Thread.currentThread().getName());
				concurrentHashMap.put((int) Math.random(), Thread.currentThread().getName());
			}
		};
		Runnable consumer = new Runnable() {
			public void run() {
				System.out.println("Read:" + Thread.currentThread().getName());
			}
		};

		for (int i = 0; i < 10000; i++) {
			new Thread(producer).start();
			new Thread(consumer).start();
		}
		System.out.println(System.currentTimeMillis() - startTime);
	}
}
